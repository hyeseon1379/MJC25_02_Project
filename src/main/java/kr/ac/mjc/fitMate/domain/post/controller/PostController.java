package kr.ac.mjc.fitMate.domain.post.controller;

import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;  // 🔥 추가
import java.util.List;                                  // 🔥 추가

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // ✅ 고민 글 작성 (저장)
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

    // ✅ 고민 글 작성 폼
    @GetMapping("/post/new")
    public String showPostForm() {
        return "post-form"; // templates/post-form.html
    }

    // ✅ 게시글 상세 조회
    @GetMapping("/post/{postId}")
    public String viewPostForm(@PathVariable("postId") Long postId, Model model) {
        PostResponse viewPost = postService.viewPostForm(postId);
        model.addAttribute("post", viewPost);
        return "post-view";// templates/post-view.html (나중에 만들 것)
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
}
