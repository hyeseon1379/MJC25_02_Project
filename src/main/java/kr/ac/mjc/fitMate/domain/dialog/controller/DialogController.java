package kr.ac.mjc.fitMate.domain.dialog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DialogController {
    @GetMapping("/dialog")
    public String dialog() {
        return "dialog";
    }
}