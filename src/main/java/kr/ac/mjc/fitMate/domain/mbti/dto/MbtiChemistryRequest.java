package kr.ac.mjc.fitMate.domain.mbti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MbtiChemistryRequest {

    @NotBlank(message = "첫 번째 MBTI를 입력해주세요.")
    @Pattern(regexp = "^(ENFP|INFP|ENTP|INTP|ENFJ|INFJ|ENTJ|INTJ|ESFP|ISFP|ESTP|ISTP|ESFJ|ISFJ|ESTJ|ISTJ)$",
            message = "올바른 MBTI 타입을 입력해주세요.")
    private String mbti1;

    @NotBlank(message = "두 번째 MBTI를 입력해주세요.")
    @Pattern(regexp = "^(ENFP|INFP|ENTP|INTP|ENFJ|INFJ|ENTJ|INTJ|ESFP|ISFP|ESTP|ISTP|ESFJ|ISFJ|ESTJ|ISTJ)$",
            message = "올바른 MBTI 타입을 입력해주세요.")
    private String mbti2;
}