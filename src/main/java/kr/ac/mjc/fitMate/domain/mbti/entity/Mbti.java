package kr.ac.mjc.fitMate.domain.mbti.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mbti {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // ENFP, INTJ ...

    private String subtitle;  // "재기발랄한 자유로운 영혼"

    private String imageUrl;  // "/images/mbti/ENFP.png"

    @Column(columnDefinition = "TEXT")
    private String mainDescription;  // 상단 캐릭터 아래 설명

    // 연애 & 친밀 성향
    @Column(columnDefinition = "TEXT")
    private String basicTendency;  // "우리 기본 성향부터"

    @Column(columnDefinition = "TEXT")
    private String chatStyle;  // 대화 스타일 (리스트)

    @Column(columnDefinition = "TEXT")
    private String loveExpression;  // 애정 표현 (리스트)

    @Column(columnDefinition = "TEXT")
    private String tiredPeople;  // "이런 사람 보면 피곤해 보일 수"

    // 스트레스 & 갈등 대처법
    @Column(columnDefinition = "TEXT")
    private String stressReaction;  // 스트레스 반응 (리스트)

    @Column(columnDefinition = "TEXT")
    private String recoveryMethod;  // 회복 방법 (리스트)

    @Column(columnDefinition = "TEXT")
    private String stressNote;  // 하단 참고사항

    // 궁합 분석
    @Column(columnDefinition = "TEXT")
    private String goodMatch;  // "INTJ||INFJ||ENFJ"

    @Column(columnDefinition = "TEXT")
    private String goodMatchDescription;  // 잘 맞는 이유

    @Column(columnDefinition = "TEXT")
    private String cautionMatch;  // "ISTJ||ISFJ||ESTJ"

    @Column(columnDefinition = "TEXT")
    private String cautionMatchDescription;  // 주의가 필요한 이유

    // 태그 (캐릭터 주변)
    private String tag1;  // "열정적인"
    private String tag2;  // "사교적인"
    private String tag3;  // "호기심많은"
}