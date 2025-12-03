package kr.ac.mjc.fitMate.test;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.fitMate.domain.user.dto.UserResponse;
import kr.ac.mjc.fitMate.domain.user.entity.Gender;
import kr.ac.mjc.fitMate.domain.user.entity.Role;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import kr.ac.mjc.fitMate.global.entity.Mbti;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
@Profile("secret")
@RequiredArgsConstructor
public class DevLoginAutoFilter implements Filter {

    private final UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        // 이미 로그인 상태면 패스
        if (session.getAttribute("loginUser") == null) {
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new IllegalArgumentException("테스트 유저가 DB에 없음"));

            UserResponse response1 = UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .gender(user.getGender())
                    .mbti(user.getMbti())
                    .trouble(user.getTrouble())
                    .build();

            session.setAttribute("loginUser", response1);
        }

        chain.doFilter(request, response);
    }
}