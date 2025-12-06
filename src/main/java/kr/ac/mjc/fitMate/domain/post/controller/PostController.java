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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;  // 🔥 추가
import java.util.List;                                  // 🔥 추가

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

    // 게시글 상세 조회
    @GetMapping("/post/{postId}")
    public String viewPostForm(@PathVariable("postId") Long postId, Model model, HttpSession session) {
        PostResponse viewPost = postService.viewPostForm(postId);

        List<CommentResponse> comments = commentService.findCommentsByPostId(postId);

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        Long currentUserId = (loginUser != null) ? loginUser.getId() : null; // 로그인이 안 되어 있으면 null

        model.addAttribute("post", viewPost);
        model.addAttribute("comments", comments);
        model.addAttribute("currentUserId", currentUserId);
        return "post-view";
    }

    // ✅ 수정 기능 추가: 고민 목록 조회 (전체 + trouble별)
    /*
     *  - /post                 → 전체 목록
     *  - /post?trouble=연애   → trouble=연애인 글만
     */
    @GetMapping("/post")
    public String listPosts(
            @RequestParam(required = false) String trouble,
            Model model) {

        List<PostResponse> posts;

        if (trouble == null || trouble.isBlank()) {
            posts = postService.getPostList();               // 전체 조회
        } else {
            posts = postService.getPostListByTrouble(trouble); // trouble별 조회
        }

        model.addAttribute("posts", posts);
        model.addAttribute("trouble", trouble);

        return "consult-list";  // templates/consult-list.html
    }

    @GetMapping("/post/update")
    public String updatePost() {
        return "post-update";
    }

    /**
     * 수정 페이지 이동
     */
    @GetMapping("/post/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {
        PostResponse post = postService.getPost(id);
        model.addAttribute("post", post); // 기존 데이터 전달
        return "post-update"; // 수정 화면
    }

    /**
     * 수정 처리
     */
    @PostMapping("/post/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute PostRequest request, HttpSession session
    ) {
        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getId();
        postService.updatePost(id, request, userId);

        return "redirect:/post/" + id; // 수정 후 상세 페이지로 이동
    }

    @PostMapping("/post/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session, Model model) {
        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getId();
        postService.deletePost(id, userId);

        model.addAttribute("postId", id);

        return "redirect:/post";
    }

}