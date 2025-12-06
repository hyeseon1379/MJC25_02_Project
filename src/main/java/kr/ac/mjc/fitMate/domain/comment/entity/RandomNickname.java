    package kr.ac.mjc.fitMate.domain.comment.entity;

    import jakarta.persistence.*;
    import kr.ac.mjc.fitMate.domain.user.entity.User;
    import kr.ac.mjc.fitMate.global.entity.BaseEntity;
    import lombok.*;

    // User ID와 Post ID별 익명 닉네임 매핑 테이블
    @Entity
    @Table(name = "random_nickname", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "postId"})})
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class RandomNickname extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String pseudonym; // 익명 이름 (예: 익명의 작성자 7105)

        @Column(name = "post_id", nullable = false)
        private Long postId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;
    }