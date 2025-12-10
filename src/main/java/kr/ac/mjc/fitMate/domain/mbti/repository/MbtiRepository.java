package kr.ac.mjc.fitMate.domain.mbti.repository;

import kr.ac.mjc.fitMate.domain.mbti.entity.Mbti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MbtiRepository extends JpaRepository<Mbti, Long> {
    Optional<Mbti> findByType(String type);
}