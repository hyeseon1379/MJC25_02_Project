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

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long savePost(PostRequest dto) {
        User user = userRepository.findById(1L)
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
    public PostResponse viewPostForm(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
        return new PostResponse(post);
    }
}
