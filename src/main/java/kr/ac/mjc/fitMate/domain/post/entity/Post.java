package kr.ac.mjc.fitMate.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String content;

    private String writer;

    private String category;

    // 생성자 (편의상)
    public Post(String title, String content, String writer, String category) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }
}

