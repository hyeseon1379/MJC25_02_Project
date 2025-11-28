package kr.ac.mjc.fitMate.domain.user.dto;

import lombok.*;
import kr.ac.mjc.fitMate.domain.user.entity.*;
import kr.ac.mjc.fitMate.global.entity.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private String nickname;
    private Gender gender;
    private Mbti mbti;
    private Trouble trouble;
}
