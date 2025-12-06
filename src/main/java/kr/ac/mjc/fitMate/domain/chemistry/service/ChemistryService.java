package kr.ac.mjc.fitMate.domain.chemistry.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.fitMate.domain.chemistry.dto.*;
import kr.ac.mjc.fitMate.domain.chemistry.entity.ChemistryResult;
import kr.ac.mjc.fitMate.domain.chemistry.repository.ChemistryRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChemistryService {

    private final ChemistryRepository repo;

    @Transactional
    public ChemistryResultDto analyze(ChemistryRequest req, Long sessionUserId) {

        int ageScore = calculateAgeScore(req.getUserBirth(), req.getPartnerBirth());
        int relationWeight = relationTypeWeight(req.getRelationType());

        int total = Math.min(100, ageScore + relationWeight);

        ChemistryResultDto dto = buildResultDto(req, total);

        // 결과 DB 저장 (선택 사항)
        ChemistryResult ent = ChemistryResult.builder()
                .userId(sessionUserId)
                .relationType(req.getRelationType())
                .userName(req.getUserName())
                .partnerName(req.getPartnerName())
                .createdAt(LocalDateTime.now())
                .score(total)
                .summary(dto.getHeadline())
                .details(buildDetailsHtml(dto))
                .build();

        repo.save(ent);
        dto.setId(ent.getId());

        return dto;
    }

    private ChemistryResultDto buildResultDto(ChemistryRequest r, int total) {
        String temp = total + "°C";
        String headline = pickHeadline(total);

        String warning = generateWarning(total);
        String tipsHtml = generateTipsHtml(total);

        return ChemistryResultDto.builder()
                .totalScore(total)
                .temperatureText(temp)
                .headline(headline)
                .warningPoint(warning)
                .tipsHtml(tipsHtml)
                .build();
    }

    // 나이 점수 계산
    private int calculateAgeScore(LocalDate a, LocalDate b) {
        if (a==null || b==null) return 0;

        int diff = Math.abs(Period.between(a,b).getYears());
        if (diff==0) return 15;
        if (diff<=3) return 12;
        if (diff<=5) return 8;
        return 3;
    }

    private int relationTypeWeight(String rel) {
        switch (rel) {
            case "love": return 15;
            case "friend": return 10;
            case "colleague": return 5;
            default: return 8;
        }
    }

    // 메시지 / 텍스트 helper
    private String pickHeadline(int total) {
        if (total >= 80) return "서로의 부족함을 채워주는 완벽한 보색 관계";
        if (total >= 60) return "상호 보완 되는 좋은 궁합입니다";
        return "서로 노력하면 더 가까워질 수 있어요";
    }

    private String generateWarning(int total) {
        return total < 50 ? "주의: 가치관 차이 가능성 있음" : "주의사항 없음";
    }

    private String generateTipsHtml(int total) {
        return "<div class='tips-grid'> ... </div>";
    }

    private String buildDetailsHtml(ChemistryResultDto dto) {
        return "<h3>상세분석</h3>";
    }

    public ChemistryResultDto loadResultDto(Long id) {
        ChemistryResult e = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다. id=" + id));

        String tempText = e.getScore() + "°C";

        return ChemistryResultDto.builder()
                .id(e.getId())
                .totalScore(e.getScore())
                .temperatureText(tempText)
                .headline(e.getSummary())
                .warningPoint(null)
                .tipsHtml(e.getDetails())
                .build();
    }
}