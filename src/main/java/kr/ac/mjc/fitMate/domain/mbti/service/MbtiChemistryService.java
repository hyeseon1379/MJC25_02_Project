package kr.ac.mjc.fitMate.domain.mbti.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.mjc.fitMate.ai.GeminiService;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiChemistryRequest;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiChemistryResponse;
import kr.ac.mjc.fitMate.domain.mbti.entity.MbtiChemistry;
import kr.ac.mjc.fitMate.domain.mbti.repository.MbtiChemistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MbtiChemistryService {

    private final MbtiChemistryRepository repository;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;

    /**
     * MBTI 궁합 분석
     */
    public MbtiChemistryResponse analyzeCompatibility(MbtiChemistryRequest request) {
        log.info("MBTI 궁합 분석 시작: {} - {}", request.getMbti1(), request.getMbti2());

        // 1. 캐시 확인 (선택사항 - 최근 1시간 이내 결과가 있으면 재사용)
        // Optional<MbtiChemistry> cached = repository.findLatestByMbtiPair(
        //     request.getMbti1(),
        //     request.getMbti2()
        // );
        // if (cached.isPresent() && isCacheValid(cached.get())) {
        //     return convertToResponse(cached.get());
        // }

        // 2. Gemini API 호출
        MbtiChemistryResponse.GeminiParsedResponse geminiResponse =
                callGeminiForCompatibility(request.getMbti1(), request.getMbti2());

        // 3. DB 저장
        MbtiChemistry entity = saveCompatibilityResult(request, geminiResponse);

        // 4. Response DTO 변환 및 반환
        return convertToResponse(entity);
    }

    /**
     * Gemini API 호출 및 파싱
     */
    private MbtiChemistryResponse.GeminiParsedResponse callGeminiForCompatibility(
            String mbti1,
            String mbti2
    ) {
        try {
            // 프롬프트 생성
            String prompt = createPrompt(mbti1, mbti2);

            // Gemini API 호출
            String responseText = geminiService.getCompletion(prompt);

            log.debug("Gemini 원본 응답: {}", responseText);

            // JSON 추출 및 파싱
            String jsonText = extractJsonFromText(responseText);

            log.debug("추출된 JSON: {}", jsonText);

            return objectMapper.readValue(
                    jsonText,
                    MbtiChemistryResponse.GeminiParsedResponse.class
            );

        } catch (JsonProcessingException e) {
            log.error("Gemini 응답 파싱 실패", e);
            throw new RuntimeException("AI 분석 결과를 처리하는 중 오류가 발생했습니다.", e);
        } catch (Exception e) {
            log.error("Gemini API 호출 실패", e);
            throw new RuntimeException("AI 궁합 분석 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * Gemini에게 보낼 프롬프트 생성
     */
    private String createPrompt(String mbti1, String mbti2) {
        return String.format("""
            당신은 MBTI 궁합 전문가입니다. %s와 %s의 연애 궁합을 분석해주세요.
            
            다음 JSON 형식으로 정확하게 답변해주세요:
            {
              "temperature": 0-100 사이의 궁합 온도 (숫자만),
              "title": "궁합을 한 문장으로 표현 (큰따옴표 안에 작성)",
              "subtitle": "궁합도 설명 (예: 높은 궁합도를 보이고 있어요)",
              "overallAnalysis": "두 MBTI의 전반적인 궁합 분석 (2-3문장)",
              "tags": ["특징1", "특징2", "특징3"],
              "synergyPoint": "두 MBTI가 서로 보완하는 긍정적인 부분 (2-3문장)",
              "cautionPoint": "주의해야 할 충돌 가능성 (2-3문장)",
              "warningText": "관계 유지를 위한 주의사항 (2-3문장)",
              "aiAdvice": [
                {"target": "%s → %s", "advice": "조언 내용"},
                {"target": "%s → %s", "advice": "조언 내용"},
                {"target": "함께 지킬 약속", "advice": "조언 내용"}
              ],
              "developmentTips": [
                {"icon": "❤️", "title": "팁 제목1", "content": "팁 내용1 (2-3문장)"},
                {"icon": "🔍", "title": "팁 제목2", "content": "팁 내용2 (2-3문장)"},
                {"icon": "📚", "title": "팁 제목3", "content": "팁 내용3 (2-3문장)"},
                {"icon": "🎁", "title": "팁 제목4", "content": "팁 내용4 (2-3문장)"}
              ]
            }
            
            주의사항:
            - 반드시 유효한 JSON 형식으로만 답변하세요
            - 모든 문자열은 큰따옴표로 감싸세요
            - temperature는 숫자만 입력하세요
            - 한국어로 작성하세요
            - JSON 이외의 다른 텍스트는 포함하지 마세요
            - 각 조언과 팁은 구체적이고 실용적으로 작성하세요
            """, mbti1, mbti2, mbti1, mbti2, mbti2, mbti1);
    }

    /**
     * 텍스트에서 JSON 추출
     */
    private String extractJsonFromText(String text) {
        text = text.trim();

        // ```json ... ``` 형태 제거
        if (text.startsWith("```json")) {
            text = text.substring(7);
        } else if (text.startsWith("```")) {
            text = text.substring(3);
        }

        if (text.endsWith("```")) {
            text = text.substring(0, text.length() - 3);
        }

        return text.trim();
    }

    /**
     * DB에 결과 저장
     */
    private MbtiChemistry saveCompatibilityResult(
            MbtiChemistryRequest request,
            MbtiChemistryResponse.GeminiParsedResponse geminiResponse
    ) {
        try {
            MbtiChemistry entity = MbtiChemistry.builder()
                    .mbti1(request.getMbti1())
                    .mbti2(request.getMbti2())
                    .temperature(geminiResponse.getTemperature())
                    .title(geminiResponse.getTitle())
                    .subtitle(geminiResponse.getSubtitle())
                    .overallAnalysis(geminiResponse.getOverallAnalysis())
                    .tags(String.join(",", geminiResponse.getTags()))
                    .synergyPoint(geminiResponse.getSynergyPoint())
                    .cautionPoint(geminiResponse.getCautionPoint())
                    .warningText(geminiResponse.getWarningText())
                    .aiAdvice(objectMapper.writeValueAsString(geminiResponse.getAiAdvice()))
                    .developmentTips(objectMapper.writeValueAsString(geminiResponse.getDevelopmentTips()))
                    .build();

            return repository.save(entity);

        } catch (JsonProcessingException e) {
            log.error("데이터 저장 실패", e);
            throw new RuntimeException("분석 결과 저장 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * Entity를 Response DTO로 변환
     */
    private MbtiChemistryResponse convertToResponse(MbtiChemistry entity) {
        try {
            List<String> tags = Arrays.asList(entity.getTags().split(","));

            List<MbtiChemistryResponse.AiAdvice> aiAdvice =
                    objectMapper.readValue(
                            entity.getAiAdvice(),
                            new TypeReference<List<MbtiChemistryResponse.AiAdvice>>() {}
                    );

            List<MbtiChemistryResponse.DevelopmentTip> developmentTips =
                    objectMapper.readValue(
                            entity.getDevelopmentTips(),
                            new TypeReference<List<MbtiChemistryResponse.DevelopmentTip>>() {}
                    );

            return MbtiChemistryResponse.builder()
                    .id(entity.getId())
                    .mbti1(entity.getMbti1())
                    .mbti2(entity.getMbti2())
                    .temperature(entity.getTemperature())
                    .title(entity.getTitle())
                    .subtitle(entity.getSubtitle())
                    .overallAnalysis(entity.getOverallAnalysis())
                    .tags(tags)
                    .synergyPoint(entity.getSynergyPoint())
                    .cautionPoint(entity.getCautionPoint())
                    .warningText(entity.getWarningText())
                    .aiAdvice(aiAdvice)
                    .developmentTips(developmentTips)
                    .createdAt(entity.getCreatedAt())
                    .build();

        } catch (JsonProcessingException e) {
            log.error("DTO 변환 실패", e);
            throw new RuntimeException("데이터 변환 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * ID로 궁합 결과 조회
     */
    @Transactional(readOnly = true)
    public MbtiChemistryResponse getCompatibilityById(Long id) {
        MbtiChemistry entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("궁합 분석 결과를 찾을 수 없습니다."));

        return convertToResponse(entity);
    }
}