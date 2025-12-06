package kr.ac.mjc.fitMate.domain.comment.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.fitMate.domain.comment.dto.CommentRequest;
import kr.ac.mjc.fitMate.domain.comment.dto.CommentResponse;
import kr.ac.mjc.fitMate.domain.comment.service.CommentService;
import kr.ac.mjc.fitMate.domain.post.service.PostService;
import kr.ac.mjc.fitMate.domain.user.dto.UserResponse;
import kr.ac.mjc.fitMate.domain.user.dto.UserUpdateRequest;
import kr.ac.mjc.fitMate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/comment")
    public String createComment(CommentRequest dto, HttpSession session) {

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        dto.setUserId(loginUser.getId());

        dto.setWriter(loginUser.getNickname());

        commentService.saveComment(dto);

        // 댓글수 증가
        postService.incrementCommentCount(dto.getPostId());

        return "redirect:/post/" + dto.getPostId();
    }


    // 댓글/답글 수정
    @PostMapping("/comment/edit/{id}")
    public String updateComment(@PathVariable("id") Long commentId,
                                @RequestParam Long postId,
                                @RequestParam String content,
                                HttpSession session,
                                RedirectAttributes rttr) {

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        // 1. 로그인 검증
        if (loginUser == null) {
            rttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        // 2. 권한 검증
        boolean authorized = commentService.isCommentAuthor(commentId, loginUser.getId());

        if (!authorized) {
            rttr.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/post/" + postId;
        }

        // 3. Service 호출 및 수정
        commentService.updateComment(commentId, content);
        rttr.addFlashAttribute("message", "댓글이 성공적으로 수정되었습니다.");

        return "redirect:/post/" + postId;
    }

    // 댓글/답글 삭제
    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long commentId,
                                @RequestParam Long postId,
                                HttpSession session,
                                RedirectAttributes rttr) {

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        // 1. 로그인 검증
        if (loginUser == null) {
            rttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        // 2. 권한 검증
        boolean authorized = commentService.isCommentAuthor(commentId, loginUser.getId());

        if (!authorized) {
            rttr.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
            return "redirect:/post/" + postId;
        }

        // 3. Service 호출 및 삭제
        commentService.deleteComment(commentId);

        // 댓글수 감소
        postService.decrementCommentCount(postId);

        rttr.addFlashAttribute("message", "댓글이 삭제되었습니다.");

        return "redirect:/post/" + postId;
    }

    @GetMapping("/mycomments")
    public String viewMyComments(HttpSession session, Model model) {
        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        Long userId = loginUser.getId();

        // 사용자 정보도 함께 전달
        UserResponse userInfo = userService.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("updateRequest", new UserUpdateRequest());

        List<CommentResponse> myComments = commentService.getMyComments(userId);
        model.addAttribute("myComments", myComments);
        model.addAttribute("currentTab", "comments");
        model.addAttribute("myPosts", null);

        return "mypage";
    }
}