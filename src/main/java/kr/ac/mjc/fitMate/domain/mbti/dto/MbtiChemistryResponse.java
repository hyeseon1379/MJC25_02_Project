package kr.ac.mjc.fitMate.domain.mbti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MbtiChemistryResponse {

    private Long id;
    private String mbti1;
    private String mbti2;
    private Integer temperature;
    private String title;
    private String subtitle;
    private String overallAnalysis;
    private List<String> tags;
    private String synergyPoint;
    private String cautionPoint;
    private String warningText;
    private List<AiAdvice> aiAdvice;
    private List<DevelopmentTip> developmentTips;
    private LocalDateTime createdAt;

    /**
     * AI 조언 내부 클래스
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AiAdvice {
        private String target;  // "ENFP → INTJ", "INTJ → ENFP", "함께 지킬 약속"
        private String advice;
    }

    /**
     * 관계 발전 팁 내부 클래스
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DevelopmentTip {
        private String icon;
        private String title;
        private String content;
    }

    /**
     * Gemini API 응답 파싱용 DTO (내부 사용)
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeminiParsedResponse {
        private Integer temperature;
        private String title;
        private String subtitle;
        private String overallAnalysis;
        private List<String> tags;
        private String synergyPoint;
        private String cautionPoint;
        private String warningText;
        private List<AiAdvice> aiAdvice;
        private List<DevelopmentTip> developmentTips;
    }
}