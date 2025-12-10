package kr.ac.mjc.fitMate.domain.user.dto;

import kr.ac.mjc.fitMate.domain.user.entity.Gender;
import kr.ac.mjc.fitMate.global.entity.Mbti;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String nickname;

    private LocalDate birth;

    private Mbti mbti;

    private Gender gender;

    private Trouble trouble;

}