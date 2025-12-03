package kr.ac.mjc.fitMate.domain.chemistry.controller;

import kr.ac.mjc.fitMate.domain.chemistry.dto.*;
import kr.ac.mjc.fitMate.domain.chemistry.service.ChemistryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/chemistry")
@RequiredArgsConstructor
public class ChemistryController {

    private final ChemistryService service;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("request", new ChemistryRequest());
        return "chemistry-form";
    }

    @PostMapping("/analyze")
    public String analyze(@ModelAttribute ChemistryRequest request, HttpSession session) {
        Long userId = session.getAttribute("loginUser") != null ? (Long) session.getAttribute("loginUser") : null;
        ChemistryResultDto result = service.analyze(request, userId);
        return "redirect:/chemistry/result/" + result.getId();
    }

    @GetMapping("/result/{id}")
    public String result(@PathVariable Long id, Model model) {
        ChemistryResultDto dto = service.loadResultDto(id);
        model.addAttribute("result", dto);
        return "chemistry-result";
    }
}
