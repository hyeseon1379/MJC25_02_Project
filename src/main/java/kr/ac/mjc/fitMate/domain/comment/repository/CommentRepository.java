package kr.ac.mjc.fitMate.domain.comment.repository;

import kr.ac.mjc.fitMate.domain.comment.entity.Comment;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost_Id(Long postId);

    List<Comment> findByUserOrderByIdDesc(User user);
}