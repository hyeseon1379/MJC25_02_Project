package kr.ac.mjc.fitMate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String menu() {
        return "main";
    }
    
    @GetMapping("/dialog")
    public String dialog() {
        return "dialog";
    }
}
