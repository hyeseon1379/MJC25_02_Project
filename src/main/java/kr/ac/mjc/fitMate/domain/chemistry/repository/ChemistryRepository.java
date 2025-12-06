package kr.ac.mjc.fitMate.domain.chemistry.repository;

import kr.ac.mjc.fitMate.domain.chemistry.entity.ChemistryResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemistryRepository extends JpaRepository<ChemistryResult, Long> {
    // 기본 CRUD 사용
}
