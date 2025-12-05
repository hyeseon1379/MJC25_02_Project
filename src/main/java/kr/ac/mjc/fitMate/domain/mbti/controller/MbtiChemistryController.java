package kr.ac.mjc.fitMate.domain.mbti.controller;

import jakarta.validation.Valid;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiChemistryRequest;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiChemistryResponse;
import kr.ac.mjc.fitMate.domain.mbti.service.MbtiChemistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mbti")
@RequiredArgsConstructor
@Slf4j
public class MbtiChemistryController {

    private final MbtiChemistryService service;

    /**
     * MBTI 궁합 선택 페이지
     */
    @GetMapping("/chemistry")
    public String chemistryPage() {
        return "mbti/mbti-chemistry";
    }

    /**
     * MBTI 궁합 분석 API (AJAX 요청용)
     */
    @PostMapping("/analyze")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> analyzeCompatibility(
            @Valid @RequestBody MbtiChemistryRequest request,
            BindingResult bindingResult
    ) {
        log.info("MBTI 궁합 분석 요청: {} - {}", request.getMbti1(), request.getMbti2());

        Map<String, Object> response = new HashMap<>();

        // 유효성 검사 실패
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            response.put("success", false);
            response.put("message", errorMessage);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            MbtiChemistryResponse result = service.analyzeCompatibility(request);
            response.put("success", true);
            response.put("message", "궁합 분석이 완료되었습니다.");
            response.put("data", result);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("MBTI 궁합 분석 실패", e);
            response.put("success", false);
            response.put("message", "궁합 분석 중 오류가 발생했습니다. 다시 시도해주세요.");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * MBTI 궁합 결과 페이지
     */
    @GetMapping("/result/{id}")
    public String resultPage(@PathVariable Long id, Model model) {
        try {
            MbtiChemistryResponse result = service.getCompatibilityById(id);
            model.addAttribute("result", result);
            return "mbti/mbti-chemistry-result";

        } catch (Exception e) {
            log.error("결과 조회 실패: id={}", id, e);
            return "redirect:/mbti/chemistry";
        }
    }
}