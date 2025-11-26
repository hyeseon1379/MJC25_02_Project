package kr.ac.mjc.fitMate.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class GeminiSimpleController {
    private final WebClient webClient = WebClient.create("https://generativelanguage.googleapis.com");

    @Value("${gemini.api.key}")
    private String apiKey;
    //test1
    @PostMapping("/gemini/simple")
    public Mono<String> callGemini(@RequestBody String input) {
        Map<String, Object> body = Map.of(
                "contents", List.of(Map.of(
                        "parts", List.of(Map.of("text", "간단히 대답해줘:\n\n" + input))
                ))
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.0-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }


    //test2
    private final GeminiService geminiService;

    @Autowired
    public GeminiSimpleController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/api/text")
    public String text(@RequestParam("prompt") String prompt){
        return geminiService.getCompletion(prompt);
    }

    @PostMapping("/api/analyze-image")
    public String analyzeImage(
            @RequestParam("prompt") String prompt,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        // 1. MultipartFile의 바이트 배열을 Base64로 인코딩
        byte[] imageBytes = imageFile.getBytes();
        String base64Data = Base64.getEncoder().encodeToString(imageBytes);

        // 2. 이미지 MIME 타입 확인 및 DTO 생성
        String mimeType = imageFile.getContentType();
        if (mimeType == null || !mimeType.startsWith("image/")) {
            // 적절한 예외 처리 필요
            throw new IllegalArgumentException("올바른 이미지 파일 형식이 아닙니다.");
        }

        GeminiRequest.InlineData inlineData = new GeminiRequest.InlineData(
                mimeType,
                base64Data
        );

        // 3. 서비스 호출 및 결과 반환
        return geminiService.getCompletionWithImage(prompt, inlineData);
    }

}