package kr.ac.mjc.fitMate.domain.post.dto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private String title;
    private String content;
    private String writer;
    private String category;
}
