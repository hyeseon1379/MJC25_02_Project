package kr.ac.mjc.fitMate.domain.mbti.repository;

import kr.ac.mjc.fitMate.domain.mbti.entity.MbtiChemistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MbtiChemistryRepository extends JpaRepository<MbtiChemistry, Long> {

    // 두 MBTI 조합으로 최근 분석 결과 찾기 (순서 무관)
    @Query("SELECT m FROM MbtiChemistry m WHERE " +
            "(m.mbti1 = :mbti1 AND m.mbti2 = :mbti2) OR " +
            "(m.mbti1 = :mbti2 AND m.mbti2 = :mbti1) " +
            "ORDER BY m.createdAt DESC LIMIT 1")
    Optional<MbtiChemistry> findLatestByMbtiPair(
            @Param("mbti1") String mbti1,
            @Param("mbti2") String mbti2
    );
}