package kr.ac.mjc.fitMate.domain.mbti.dto;

import kr.ac.mjc.fitMate.domain.mbti.entity.Mbti;

public record MbtiListResponse(
        String type,
        String description
) {
    public static MbtiListResponse from(Mbti entity) {
        return new MbtiListResponse(
                entity.getType(),
                entity.getSubtitle()
        );
    }
}