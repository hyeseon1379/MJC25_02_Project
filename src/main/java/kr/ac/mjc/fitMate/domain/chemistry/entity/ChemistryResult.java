package kr.ac.mjc.fitMate.domain.chemistry.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="chemistry_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChemistryResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String relationType;
    private String userName;
    private String partnerName;
    private LocalDateTime createdAt;
    private int score;
    @Lob
    private String summary;
    @Lob
    private String details;
}
