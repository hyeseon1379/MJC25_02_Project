package kr.ac.mjc.fitMate.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentRequest {
    private Long postId;    // 댓글/답글이 달릴 게시글 ID
    private String content; // 내용
    private Long userId;    // 작성자 User ID (인증 후 Service에서 Post/User 객체를 찾기 위함)
    private String writer;  // 익명 이름
    private Long parentId;  // 부모 댓글 ID (null이면 최상위 댓글, 값 있으면 대댓글)
}