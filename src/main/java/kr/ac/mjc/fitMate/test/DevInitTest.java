package kr.ac.mjc.fitMate.test;

import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.post.repository.PostRepository;
import kr.ac.mjc.fitMate.domain.user.entity.Gender;
import kr.ac.mjc.fitMate.domain.user.entity.Role;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.domain.user.repository.UserRepository;
import kr.ac.mjc.fitMate.global.entity.Mbti;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DevInitTest implements CommandLineRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        User user = User.builder()
                .email("user@example.com")
                .password("password")
                .birth(LocalDate.of(2004,6,4))
                .nickname("nick1")
                .username("user1")
                .role(Role.USER)
                .gender(Gender.FEMALE)
                .mbti(Mbti.INTP)
                .trouble(Trouble.FRIENDS)
                .build();

        userRepository.save(user);

        User user2 = User.builder()
                .email("user2@example.com")
                .password("password")
                .birth(LocalDate.of(2004,6,4))
                .nickname("nick2")
                .username("user2")
                .role(Role.USER)
                .gender(Gender.MALE)
                .mbti(Mbti.ENFP)
                .trouble(Trouble.LOVE)
                .build();

        userRepository.save(user2);

        Post post = Post.builder()
                .title("title")
                .content("content")
                .writer("익명의 작성자 1234")
                .trouble(Trouble.LOVE)
                .user(user)
                .viewCount(0)
                .build();

        postRepository.save(post);

        Post post2 = Post.builder()
                .title("title2")
                .content("content2")
                .writer("익명의 작성자 5678")
                .trouble(Trouble.WORK)
                .user(user2)
                .viewCount(0)
                .build();

        postRepository.save(post2);
    }
}
