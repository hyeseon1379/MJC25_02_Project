package kr.ac.mjc.fitMate.domain.chemistry.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChemistryResultDto {
    private Long id;
    private int totalScore;          // 0~100
    private String temperatureText;
    private String headline;
    private String warningPoint;     // 주의사항 요약
    private String tipsHtml;         // 관계 발전 팁

    private String subtitle;

    private String userName;
    private String userGender;
    private String userBirth;
    private String userAvatar;

    private String partnerName;
    private String partnerGender;
    private String partnerBirth;
    private String partnerAvatar;

    private int compatibilityScore;

    private String featureSummary;

    private java.util.List<String> tags;
    private java.util.List<java.util.Map<String, Object>> tips;
    private java.util.List<java.util.Map<String, Object>> advices;
}
