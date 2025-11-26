package kr.ac.mjc.fitMate.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeminiTestController {
    @GetMapping("/test/gemini")
    public String gemini() {
        return "gemini";
    }
}
