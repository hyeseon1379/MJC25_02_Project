package kr.ac.mjc.fitMate.domain.post.controller;

import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.dto.PostResponse;
import kr.ac.mjc.fitMate.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/new")
    public String showPostForm() {
        return "post-form"; // templates/post-form.html
    }

    @PostMapping("/post/new")
    public String createPost(PostRequest dto, Model model) {
        Long savedId = postService.savePost(dto);
        // postService.savePost(dto);
        model.addAttribute("dto", dto);
        // return "post-success";
        return "redirect:/post/" + savedId;
    }

    // 게시글 조회 기능 - 지성재
    @GetMapping("/post/{postId}")
    public String viewPostForm(@PathVariable("postId") final Long postId, Model model) {
        PostResponse viewPost = postService.viewPostForm(postId);
        model.addAttribute("post", viewPost);
        return "post-view";     // templates에 나중에 post-view.html 추가
    }

    // 수정 기능 추가
}
