package kr.ac.mjc.fitMate.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.fitMate.domain.user.dto.UserLoginRequest;
import kr.ac.mjc.fitMate.domain.user.dto.UserResponse;
import kr.ac.mjc.fitMate.domain.user.dto.UserSignupRequest;
import kr.ac.mjc.fitMate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // signup.html
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(UserSignupRequest request, Model model) {
        try {
            userService.signup(request);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // 에러 있으면 다시 회원가입 페이지로
        }

        return "redirect:/login"; // 성공하면 로그인 페이지로
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(UserLoginRequest request, HttpSession session) {

        UserResponse user = userService.login(request);

        // 세션 저장
        session.setAttribute("loginUser", user);

        return "redirect:/"; // 홈으로 이동
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 전체 삭제
        return "redirect:/";
    }
}