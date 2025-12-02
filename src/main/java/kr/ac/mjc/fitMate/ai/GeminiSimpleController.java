package kr.ac.mjc.fitMate.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @PostMapping("/api/analyze-image/group")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> analyzeImage_group(
            @RequestParam("prompt") String prompt,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        byte[] imageBytes = imageFile.getBytes();
        String base64Data = Base64.getEncoder().encodeToString(imageBytes);

        String mimeType = imageFile.getContentType();
        if (mimeType == null || !mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("올바른 이미지 파일 형식이 아닙니다.");
        }

        GeminiRequest.InlineData inlineData = new GeminiRequest.InlineData(
                mimeType,
                base64Data
        );

        String result = geminiService.getCompletionWithImage(prompt, inlineData);

        // 응답 파싱
        Map<String, Object> response = parseGeminiResponse(result);

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> parseGeminiResponse(String aiResponse) {
        Map<String, Object> result = new HashMap<>();

        // 참여자 정보 파싱 (이름과 친밀도)
        List<Map<String, Object>> participants = new ArrayList<>();
        Pattern participantPattern = Pattern.compile("([가-힣○]+):\\s*(\\d+)");
        Matcher matcher = participantPattern.matcher(aiResponse);

        while (matcher.find()) {
            Map<String, Object> participant = new HashMap<>();
            participant.put("name", matcher.group(1));
            participant.put("intimacy", Integer.parseInt(matcher.group(2)));
            participants.add(participant);
        }

        // 친밀도 순으로 정렬
        participants.sort((a, b) ->
                Integer.compare((Integer)b.get("intimacy"), (Integer)a.get("intimacy"))
        );

        // 상위 3명만 선택, 부족하면 빈 데이터로 채우기
        List<Map<String, Object>> top3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i < participants.size()) {
                top3.add(participants.get(i));
            } else {
                Map<String, Object> empty = new HashMap<>();
                empty.put("name", "-");
                empty.put("intimacy", 0);
                top3.add(empty);
            }
        }

        result.put("participants", top3);

        // AI 총평 추출
        Pattern summaryPattern = Pattern.compile("AI 총평[:\\s]*(.*?)(?=맞춤 조언|$)", Pattern.DOTALL);
        Matcher summaryMatcher = summaryPattern.matcher(aiResponse);
        String summary = summaryMatcher.find() ?
                summaryMatcher.group(1).trim() : "대화 분석을 완료했습니다.";
        result.put("summary", summary);

        // 맞춤 조언 추출
        Pattern advicePattern = Pattern.compile("맞춤 조언[:\\s]*(.*?)$", Pattern.DOTALL);
        Matcher adviceMatcher = advicePattern.matcher(aiResponse);
        String advice = adviceMatcher.find() ?
                adviceMatcher.group(1).trim() : "긍정적인 대화를 이어가세요.";
        result.put("advice", advice);

        return result;
    }
}