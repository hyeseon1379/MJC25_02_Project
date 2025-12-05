package kr.ac.mjc.fitMate.domain.comment.service;

import kr.ac.mjc.fitMate.domain.comment.dto.CommentRequest;
import kr.ac.mjc.fitMate.domain.comment.dto.CommentResponse;
import kr.ac.mjc.fitMate.domain.comment.entity.Comment;
import kr.ac.mjc.fitMate.domain.comment.entity.RandomNickname;
import kr.ac.mjc.fitMate.domain.comment.repository.CommentRepository;
import kr.ac.mjc.fitMate.domain.comment.repository.RandomNicknameRepository;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final RandomNicknameRepository randomNicknameRepository;

    // 랜덤 닉네임 생성
    private String randomWriterName() {
        int randomNum = (int)(Math.random() * 9000) + 1000; // 1000~9999
        return "익명의 작성자 " + randomNum;
    }


    private String getOrCreateAnonymousName(Long userId, Long postId) {

        // 게시글 작성자인지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (post.getUser().getId().equals(userId)) {
            // 이 사용자가 게시글의 실제 작성자라면, 게시글의 익명 이름을 사용
            return post.getWriter();
        }

        // 이미 저장된 익명 이름이 있는지 확인
        Optional<RandomNickname> existingMapping = randomNicknameRepository.findByUserIdAndPostId(userId, postId);

        if (existingMapping.isPresent()) {
            // 있으면 기존 이름 반환 (일관성 유지)
            return existingMapping.get().getPseudonym();
        } else {
            // 없으면 새 이름 생성
            String newPseudonym = randomWriterName();

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("익명 닉네임 생성 중 사용자 ID를 찾을 수 없습니다."));

            // 매핑 정보를 DB에 저장
            RandomNickname newMapping = RandomNickname.builder()
                    .user(user)
                    .postId(postId)
                    .pseudonym(newPseudonym)
                    .build();
            randomNicknameRepository.save(newMapping);

            return newPseudonym;
        }
    }

    // 1. 댓글/답글 저장
    @Transactional
    public Long saveComment(CommentRequest dto) {

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID입니다: " + dto.getPostId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 ID입니다: " + dto.getUserId()));

        String finalWriterName = getOrCreateAnonymousName(dto.getUserId(), dto.getPostId());
        dto.setWriter(finalWriterName);

        Comment parentComment = null;

        if (dto.getParentId() != null) {
            parentComment = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부모 댓글 ID입니다."));
        }

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .writer(dto.getWriter())
                .user(user)
                .post(post)
                .parent(parentComment)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    // 2. 특정 게시글의 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> findCommentsByPostId(Long postId) {

        List<Comment> allComments = commentRepository.findAllByPost_Id(postId);

        Map<Long, CommentResponse> map = allComments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toMap(CommentResponse::getId, response -> response));

        List<CommentResponse> topLevelComments = new ArrayList<>();

        for (CommentResponse response : map.values()) {
            if (response.getParentId() == null) {
                // 부모 ID가 null이면 최상위 댓글 리스트에 추가
                topLevelComments.add(response);
            } else {
                // 부모 ID가 있으면 부모를 찾아서 replies 리스트에 자식을 추가
                CommentResponse parent = map.get(response.getParentId());
                if (parent != null) {
                    if (parent.getReplies() == null) {
                        parent.setReplies(new ArrayList<>());
                    }
                    try {
                        parent.getReplies().add(response);
                    } catch (UnsupportedOperationException e) {
                        List<CommentResponse> mutableReplies = new ArrayList<>(parent.getReplies());
                        mutableReplies.add(response);
                        parent.setReplies(mutableReplies);
                    }
                }
            }
        }

        // 작성 시간 순(오름차순)으로 정렬
        topLevelComments.sort(Comparator.comparing(CommentResponse::getCreateAt));

        return topLevelComments;
    }

    // 댓글/답글 수정
    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 댓글/답글을 찾을 수 없습니다. (ID: " + commentId + ")"));

        comment.setContent(content);
        commentRepository.save(comment);
    }

    // 댓글/답글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    // 댓글/답글 권한 검증
    @Transactional(readOnly = true)
    public boolean isCommentAuthor(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElse(null); // 댓글이 존재하지 않으면 null 반환

        // 댓글이 존재하지 않으면 권한 없음(false)
        if (comment == null) {
            return false;
        }

        // 댓글 작성자 ID와 로그인 사용자 ID를 비교하여 일치하면 true 반환
        return comment.getUser().getId().equals(userId);
    }
}