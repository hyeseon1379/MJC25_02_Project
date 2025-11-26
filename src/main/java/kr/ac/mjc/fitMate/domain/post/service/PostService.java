package kr.ac.mjc.fitMate.domain.post.service;

import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(PostRequest dto) {
        Post post = new Post(dto.getTitle(), dto.getContent(), dto.getCategory());
        postRepository.save(post);
    }
}
