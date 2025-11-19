package kr.ac.mjc.fitMate.domain.post.controller;

import kr.ac.mjc.fitMate.domain.post.dto.PostRequest;
import kr.ac.mjc.fitMate.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        postService.savePost(dto);
        model.addAttribute("dto", dto);
        return "post-success"; // templates/post-success.html
    }

    // 수정 기능 추가
}
