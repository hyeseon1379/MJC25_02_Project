package kr.ac.mjc.fitMate.domain.comment.entity;

import jakarta.persistence.*;
import kr.ac.mjc.fitMate.domain.post.entity.Post;
import kr.ac.mjc.fitMate.domain.user.entity.User;
import kr.ac.mjc.fitMate.global.entity.BaseEntity;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // 댓글 ID

    @Column(length = 500, nullable = false)
    private String content;         // 댓글 내용

    private String writer;          // 댓글 작성자 (익명 이름)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    @OrderBy("createAt ASC")
    private List<Comment> replies;


    public Comment(String content, String writer, Post post, User user, Comment parent) {
        this.content = content;
        this.writer = writer;
        this.post = post;
        this.user = user;
        this.parent = parent;
    }
}