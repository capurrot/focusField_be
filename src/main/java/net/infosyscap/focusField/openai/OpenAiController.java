package net.infosyscap.focusField.openai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

    private final OpenAiService openAiService;

    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/classify-mood")
    public ResponseEntity<Map<String, String>> classifyMood(@RequestBody MoodInput input) {
        String mood = openAiService.classifyMood(input.getText());
        return ResponseEntity.ok(Map.of("mood", mood));
    }

}

