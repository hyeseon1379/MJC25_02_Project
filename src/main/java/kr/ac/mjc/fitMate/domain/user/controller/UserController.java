package kr.ac.mjc.fitMate.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import kr.ac.mjc.fitMate.domain.user.dto.*;
import kr.ac.mjc.fitMate.domain.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }

    // 로그인
    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }
}
