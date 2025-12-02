package kr.ac.mjc.fitMate.domain.post.entity;

import jakarta.persistence.*;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.global.entity.BaseEntity;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Trouble trouble;

    private String writer; // 작성자 -> 익명 처리

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int viewCount;
}

