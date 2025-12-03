package kr.ac.mjc.fitMate.domain.chemistry.dto;

import lombok.*;
import java.time.LocalDate;
import kr.ac.mjc.fitMate.domain.user.entity.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChemistryRequest {
    private String relationType;
    private String userName;
    private LocalDate userBirth;
    private Gender userGender;
    private String partnerName;
    private LocalDate partnerBirth;
    private Gender partnerGender;
}
