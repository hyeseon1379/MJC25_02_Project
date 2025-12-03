package kr.ac.mjc.fitMate.domain.post.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.dto.PostResponse;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        int viewCount = post.getViewCount();
        viewCount += 1;
        post.setViewCount(viewCount);
        postRepository.save(post);
        return new PostResponse(post);
    }

    // 🔥 전체 게시글 목록 조회
    public List<PostResponse> getPostList() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::new)   // new PostResponse(post)
                .toList();
    }

    // 🔥 trouble 기준 게시글 목록 조회
    public List<PostResponse> getPostListByTrouble(String trouble) {

        Trouble troubleEnum = Trouble.fromValue(trouble);   // 문자열 → enum으로 변환

        return postRepository.findByTrouble(troubleEnum)
                .stream()
                .map(PostResponse::new)
                .toList();
    }
        /** 게시글 단건 조회 (수정 페이지용) */
        public PostResponse getPost(Long id) {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
            return new PostResponse(post);
        }

        /** 게시글 수정 */
        public void updatePost(Long id, PostRequest request, Long userId) {

            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

            if (!post.getUser().getId().equals(userId)) {
                throw new IllegalStateException("게시글 수정 권한이 없습니다.");
            }

            post.setTitle(request.getTitle());
            post.setContent(request.getContent());

            // Trouble enum 은 request 가 String이므로 매핑
            post.setTrouble(Trouble.fromValue(request.getTrouble()));

            postRepository.save(post);
        }

        public void deletePost(Long id, Long userId) {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

            if(!post.getUser().getId().equals(userId)) {
                throw new IllegalStateException("게시글 삭제 권한이 없습니다.");
            }

            postRepository.delete(post);
        }
    }



