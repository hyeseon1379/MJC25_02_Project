package kr.ac.mjc.fitMate.domain.mbti.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mbti_chemistry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MbtiChemistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private String mbti1;

    @Column(nullable = false, length = 4)
    private String mbti2;

    @Column(nullable = false)
    private Integer temperature;  // 궁합 온도 (0-100)

    @Column(length = 500)
    private String title;  // 궁합 타이틀

    @Column(length = 200)
    private String subtitle;  // 궁합 서브타이틀

    @Column(columnDefinition = "TEXT")
    private String overallAnalysis;  // 종합 분석

    @Column(length = 1000)
    private String tags;  // 태그들 (쉼표로 구분)

    @Column(columnDefinition = "TEXT")
    private String synergyPoint;  // 시너지 포인트

    @Column(columnDefinition = "TEXT")
    private String cautionPoint;  // 주의 포인트

    @Column(columnDefinition = "TEXT")
    private String warningText;  // 경고 텍스트

    @Column(columnDefinition = "TEXT")
    private String aiAdvice;  // AI 조언 (JSON 형태)

    @Column(columnDefinition = "TEXT")
    private String developmentTips;  // 관계 발전 팁 (JSON 형태)

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}