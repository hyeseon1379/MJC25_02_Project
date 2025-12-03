package kr.ac.mjc.fitMate.domain.mbti.service;

import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiDetailResponse;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiListResponse;
import kr.ac.mjc.fitMate.domain.mbti.entity.Mbti;
import kr.ac.mjc.fitMate.domain.mbti.repository.MbtiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MbtiService {

    private final MbtiRepository mbtiRepository;

    // 목록
    public List<MbtiListResponse> findAll() {
        return mbtiRepository.findAll().stream()
                .map(MbtiListResponse::from)
                .toList();
    }

    // 상세
    public MbtiDetailResponse findByType(String type) {
        Mbti mbti = mbtiRepository.findByType(type.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 MBTI: " + type));
        return MbtiDetailResponse.from(mbti);
    }
}