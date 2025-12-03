package kr.ac.mjc.fitMate.domain.dialog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DialogContoller {
    @GetMapping("/dialog")
    public String dialog() {
        return "dialog";
    }

    @GetMapping("/dialog/group")
    public String dialog_group() {
        return "dialog_group";
    }

    @GetMapping("/dialog/danger")
    public String dialog_danger() {
        return "dialog_danger";
    }
}
