package kr.ac.mjc.fitMate.domain.user.dto;

import jakarta.validation.constraints.*;
import kr.ac.mjc.fitMate.domain.user.entity.Gender;
import kr.ac.mjc.fitMate.global.entity.Mbti;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birth;

    @NotNull
    private Mbti mbti;

    @NotNull
    private Trouble trouble;
}
