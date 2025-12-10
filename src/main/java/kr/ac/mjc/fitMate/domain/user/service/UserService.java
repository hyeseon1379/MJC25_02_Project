package kr.ac.mjc.fitMate.domain.user.service;

import kr.ac.mjc.fitMate.domain.user.dto.*;
import kr.ac.mjc.fitMate.domain.user.entity.*;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import kr.ac.mjc.fitMate.global.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    public UserResponse signup(UserSignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(request.getEmail())
                //.password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword())
                .username(request.getUsername())
                .nickname(request.getNickname())
                .birth(request.getBirth())
                .gender(request.getGender())
                .mbti(request.getMbti())
                .trouble(request.getTrouble())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .trouble(user.getTrouble())
                .build();
    }

    // 로그인
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }

        if (!userRepository.existsByPassword(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .trouble(user.getTrouble())
                .build();
    }

    public UserResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .birth(user.getBirth())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .trouble(user.getTrouble())
                .build();

        return response;
    }

    @Transactional
    public void updateUserInfo(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!user.getNickname().equals(request.getNickname()) && userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
        }

        user.updateDetails(
                request.getNickname(),
                request.getBirth(),
                request.getMbti(),
                request.getGender(),
                request.getTrouble()
        );
    }
}
