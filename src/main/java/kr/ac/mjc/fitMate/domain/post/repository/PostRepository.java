package kr.ac.mjc.fitMate.domain.post.repository;

import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    // trouble 기준 게시글 조회
    List<Post> findByTroubleOrderByCreateAtDesc(Trouble trouble);
}
