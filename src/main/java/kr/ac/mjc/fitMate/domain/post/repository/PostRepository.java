package kr.ac.mjc.fitMate.domain.post.repository;

import kr.ac.mjc.fitMate.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
