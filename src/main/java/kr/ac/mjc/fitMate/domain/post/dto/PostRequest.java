package kr.ac.mjc.fitMate.domain.post.dto;
import kr.ac.mjc.fitMate.global.entity.Trouble;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest{
    private String trouble;
    private String title;
    private String content;
    private String writer;
    private String category;
}
