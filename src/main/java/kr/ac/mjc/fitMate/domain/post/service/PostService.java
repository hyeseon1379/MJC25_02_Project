package kr.ac.mjc.fitMate.domain.post.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.dto.PostResponse;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long savePost(PostRequest dto) {
        Post post = new Post(dto.getTitle(), dto.getContent(), dto.getWriter(), dto.getCategory());
        Post savedPostId = postRepository.save(post);
        return savedPostId.getId();
    }

    // 게시글 조회 기능 - 지성재
    @Transactional
    public PostResponse viewPostForm(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
        return new PostResponse(post);
    }
}
