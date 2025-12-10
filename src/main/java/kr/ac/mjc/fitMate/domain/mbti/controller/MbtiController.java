package kr.ac.mjc.fitMate.domain.mbti.controller;

import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiDetailResponse;
import kr.ac.mjc.fitMate.domain.mbti.dto.MbtiListResponse;
import kr.ac.mjc.fitMate.domain.mbti.service.MbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mbti")
public class MbtiController {

    private final MbtiService mbtiService;

    // ① MBTI 목록 페이지 (첫 번째 화면)
    @GetMapping
    public String list(Model model) {
        List<MbtiListResponse> list = mbtiService.findAll();
        model.addAttribute("mbtis", list);
        return "mbti/mbti";  // templates/mbti/mbti.html
    }

    // ② MBTI 상세 페이지 (두 번째 화면)
    @GetMapping("/{type}")
    public String detail(@PathVariable String type, Model model) {
        MbtiDetailResponse dto = mbtiService.findByType(type);
        model.addAttribute("mbti", dto);
        return "mbti/mbti-result";  // templates/mbti/mbti-result.html
    }
}