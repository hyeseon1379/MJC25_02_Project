package kr.ac.mjc.fitMate.domain.comment.dto;

import kr.ac.mjc.fitMate.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CommentResponse {
    private Long id;
    private Long postId;
    private String content;
    private String writer;

    // BaseEntity에서 상속받은 필드
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // 대댓글 관리를 위한 필드
    private Long userId;
    private Long parentId;

    private List<CommentResponse> replies;


    public CommentResponse(Comment comment) {
        this.id = comment.getId();

        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();

        this.content = comment.getContent();
        this.writer = comment.getWriter();

        this.createAt = comment.getCreateAt();
        this.updateAt = comment.getUpdateAt();

        this.parentId = comment.getParent() != null ? comment.getParent().getId() : null;

        this.replies = new ArrayList<>();
    }
}