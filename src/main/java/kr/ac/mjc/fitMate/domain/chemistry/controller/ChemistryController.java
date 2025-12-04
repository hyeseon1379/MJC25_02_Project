package kr.ac.mjc.fitMate.domain.chemistry.controller;

import kr.ac.mjc.fitMate.ai.GeminiService;
import kr.ac.mjc.fitMate.domain.chemistry.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chemistry")
public class ChemistryController {

    private final GeminiService geminiService;

    @GetMapping
    public String asdf() {
        return "chemistry-form";
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeChemistry(@RequestBody Map<String, String> request) {
        try {
            String prompt = buildPrompt(request);
            String result = geminiService.getCompletion(prompt);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"궁합 분석 중 오류가 발생했습니다.\"}");
        }
    }

    @GetMapping("/result")
    public String chemistry() {
        return "chemistry-result";
    }

    private String buildPrompt(Map<String, String> request) {
        return String.format("""
            당신은 전문 궁합 분석가입니다. 다음 두 사람의 궁합을 분석해주세요.
            
            **관계**: %s
            
            **나의 정보**
            - 이름: %s
            - 생년월일: %s
            - 성별: %s
            
            **상대방 정보**
            - 이름: %s
            - 생년월일: %s
            - 성별: %s
            
            다음 형식의 JSON으로만 응답해주세요 (다른 설명 없이):
            {
              "title": "두 사람의 궁합을 한 문장으로 표현",
              "subtitle": "부가 설명",
              "temperature": 0~100 사이의 궁합 온도,
              "description": "핵심 내용 3-4줄 설명",
              "tags": ["#태그1", "#태그2", "#태그3"],
              "blueTips": ["조언1", "조언2", "조언3", "조언4"],
              "pinkTips": ["갈등포인트1", "갈등포인트2", "갈등포인트3"],
              "advices": [
                {"emoji": "💕", "title": "제목1", "content": "내용1"},
                {"emoji": "🔍", "title": "제목2", "content": "내용2"},
                {"emoji": "🎪", "title": "제목3", "content": "내용3"},
                {"emoji": "🎁", "title": "제목4", "content": "내용4"}
              ]
            }
            """,
                request.get("relationship"),
                request.get("myName"),
                request.get("myBirthDate"),
                request.get("myGender"),
                request.get("partnerName"),
                request.get("partnerBirthDate"),
                request.get("partnerGender")
        );
    }
}