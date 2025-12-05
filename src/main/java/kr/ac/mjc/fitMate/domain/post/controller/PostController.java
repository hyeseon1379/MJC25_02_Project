package kr.ac.mjc.fitMate.domain.post.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.fitMate.domain.comment.dto.CommentResponse;
import kr.ac.mjc.fitMate.domain.comment.service.CommentService;
import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.dto.PostResponse;
import kr.ac.mjc.fitMate.domain.post.service.PostService;
import kr.ac.mjc.fitMate.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    @PostMapping("/post/new")
    public String createPost(PostRequest dto, HttpSession session, Model model) {

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getId();

        Long postId = postService.savePost(dto, userId);
        model.addAttribute("dto", dto);

        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/new")
    public String showPostForm() {
        return "post-form"; // templates/post-form.html
    }

//    @PostMapping("/post/new")
//    public String createPost(PostRequest dto, Model model) {
//        Long savedId = postService.savePost(dto);
//        // postService.savePost(dto);
//        model.addAttribute("dto", dto);
//        // return "post-success";
//        return "redirect:/post/" + savedId;
//    }

    // 게시글 조회 기능 - 지성재
    @GetMapping("/post/{postId}")
    public String viewPostForm(@PathVariable("postId") Long postId, Model model, HttpSession session) {
        PostResponse viewPost = postService.viewPostForm(postId);

        List<CommentResponse> comments = commentService.findCommentsByPostId(postId);

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        Long currentUserId = (loginUser != null) ? loginUser.getId() : null; // 로그인이 안 되어 있으면 null

        model.addAttribute("post", viewPost);
        model.addAttribute("comments", comments);
        model.addAttribute("currentUserId", currentUserId);
        return "post-view";// templates에 나중에 post-view.html 추가
    }

    // 수정 기능 추가
}
