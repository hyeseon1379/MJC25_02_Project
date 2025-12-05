package kr.ac.mjc.fitMate.domain.post.service;

import org.springframework.transaction.annotation.Transactional;
import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.dto.PostResponse;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long savePost(PostRequest dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String writerName = randomWriterName();

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .trouble(Trouble.fromValue(dto.getTrouble()))
                .user(user)
                .writer(writerName)
                .viewCount(0)
                .build();

        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    private String randomWriterName() {
        int randomNum = (int)(Math.random() * 9000) + 1000; // 1000~9999
        return "익명의 작성자 " + randomNum;
    }

    // 게시글 조회 기능 - 지성재
    @Transactional
    public PostResponse viewPostForm(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));

        post.setViewCount(post.getViewCount() + 1);

        return new PostResponse(post);
    }

    // 게시글 수정 기능
    @Transactional
    public void updatePost(Long postId, PostRequest dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 게시글을 찾을 수 없습니다."));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setTrouble(Trouble.fromValue(dto.getTrouble()));
    }

    // 게시글 삭제 기능
    @Transactional
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("삭제할 게시글을 찾을 수 없습니다.");
        }
        postRepository.deleteById(postId);
    }

    // 게시글 권한 확인
    @Transactional(readOnly = true)
    public boolean isPostAuthor(Long postId, Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);

        if (postOpt.isEmpty()) {
            return false;
        }

        Post post = postOpt.get();

        return post.getUser().getId().equals(userId);
    }

    // 게시글 댓글수 증가
    @Transactional
    public void incrementCommentCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수를 증가시킬 게시글을 찾을 수 없습니다."));

        // 댓글 수 1 증가
        post.setCommentCount(post.getCommentCount() + 1);
    }

    // 게시글 댓글수 감소
    @Transactional
    public void decrementCommentCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수를 감소시킬 게시글을 찾을 수 없습니다."));

        // 댓글 수 1 감소 (0 이하로 내려가지 않도록 함)
        int currentCount = post.getCommentCount();
        if (currentCount > 0) {
            post.setCommentCount(currentCount - 1);
        }
    }
}
