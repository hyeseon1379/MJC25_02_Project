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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
    public String viewPostForm(@PathVariable("postId") Long postId, Model model) {
        PostResponse viewPost = postService.viewPostForm(postId);
        model.addAttribute("post", viewPost);
        return "post-view";// templates에 나중에 post-view.html 추가
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

}