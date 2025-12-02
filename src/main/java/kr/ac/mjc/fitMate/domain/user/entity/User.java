package kr.ac.mjc.fitMate.domain.user.entity;

import jakarta.persistence.*;
import kr.ac.mjc.fitMate.global.entity.BaseEntity;
import kr.ac.mjc.fitMate.global.entity.Mbti;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(length = 20, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Mbti mbti;

    @Enumerated(EnumType.STRING)
    private Trouble trouble;
}
