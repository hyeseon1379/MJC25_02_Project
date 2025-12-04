package kr.ac.mjc.fitMate.domain.mbti.dto;

import kr.ac.mjc.fitMate.domain.mbti.entity.Mbti;

import java.util.Arrays;
import java.util.List;

public record MbtiDetailResponse(
        String type,
        String subtitle,
        String imageUrl,
        String mainDescription,
        String tag1,
        String tag2,
        String tag3,

        // 연애 & 친밀 성향
        String basicTendency,
        List<String> chatStyleList,
        List<String> loveExpressionList,
        String tiredPeople,

        // 스트레스 & 갈등
        List<String> stressReactionList,
        List<String> recoveryMethodList,
        String stressNote,

        // 궁합
        List<String> goodMatchList,
        String goodMatchDescription,
        List<String> cautionMatchList,
        String cautionMatchDescription
) {
    public static MbtiDetailResponse from(Mbti mbti) {
        return new MbtiDetailResponse(
                mbti.getType(),
                mbti.getSubtitle(),
                mbti.getImageUrl(),
                mbti.getMainDescription(),
                mbti.getTag1(),
                mbti.getTag2(),
                mbti.getTag3(),

                mbti.getBasicTendency(),
                parseList(mbti.getChatStyle()),
                parseList(mbti.getLoveExpression()),
                mbti.getTiredPeople(),

                parseList(mbti.getStressReaction()),
                parseList(mbti.getRecoveryMethod()),
                mbti.getStressNote(),

                parseList(mbti.getGoodMatch()),
                mbti.getGoodMatchDescription(),
                parseList(mbti.getCautionMatch()),
                mbti.getCautionMatchDescription()
        );
    }

    // "항목1||항목2||항목3" 형식을 List로 변환
    private static List<String> parseList(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return Arrays.stream(text.split("\\|\\|"))
                .map(String::trim)
                .toList();
    }
}