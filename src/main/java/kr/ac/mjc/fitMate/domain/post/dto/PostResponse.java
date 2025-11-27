package kr.ac.mjc.fitMate.domain.post.dto;

import kr.ac.mjc.fitMate.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponse {

    // 게시글 상세 조회 기능 추가 - 지성재
    private Long id;                    // 게시글 ID
    private String title;               // 게시글 제목
    private String content;             // 게시글 내용
    private String writer;              // 게시글 작성자
    private LocalDateTime createdAt;    // 게시글 작성 시간
    private int viewCount;              // 게시글 조회수
    private int commentCount;           // 게시글 댓글수
    private String category;            // 게시글 카테고리

    /* public PostResponse(Post post) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = null;
        this.viewCount = 0;
        this.commentCount = 0;
    }
     */

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
        this.category = post.getCategory();
        this.createdAt = LocalDateTime.now();
        this.viewCount = 0;
        this.commentCount = 0;
    }

}
