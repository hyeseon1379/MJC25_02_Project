package kr.ac.mjc.fitMate.domain.comment.repository;

import kr.ac.mjc.fitMate.domain.comment.entity.RandomNickname;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RandomNicknameRepository extends JpaRepository<RandomNickname, Long> {

    // 특정 게시글과 특정 사용자 ID에 해당하는 익명 이름을 조회
    Optional<RandomNickname> findByUserIdAndPostId(Long userId, Long postId);
}