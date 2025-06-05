package net.infosyscap.focusField.openai;

import net.infosyscap.focusField.moods.MoodList;
import net.infosyscap.focusField.moods.MoodListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenAiService {

    private final MoodListRepository moodRepository;
    private final RestTemplate restTemplate;
    private final String apiKey;

    public OpenAiService(MoodListRepository moodRepository,
                         @Value("${openai.api.key}") String apiKey) {
        this.moodRepository = moodRepository;
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
    }

    public String classifyMood(String userInput) {
        List<String> moods = moodRepository.findAll()
                .stream()
                .map(MoodList::getSlug)
                .toList();

        String prompt = String.format(
                "Questi sono i mood disponibili: %s\nL’utente scrive: \"%s\"\nRispondi solo con uno dei mood esatti elencati sopra.",
                moods, userInput
        );

        OpenAiRequest request = new OpenAiRequest(
                "gpt-3.5-turbo",
                List.of(
                        new OpenAiRequest.Message("system", "Agisci come un classificatore. Scegli solo uno dei mood forniti."),
                        new OpenAiRequest.Message("user", prompt)
                ),
                0.2
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<OpenAiResponse> response = restTemplate.exchange(
                    "https://api.openai.com/v1/chat/completions",
                    HttpMethod.POST,
                    entity,
                    OpenAiResponse.class
            );

            return response.getBody()
                    .choices.get(0)
                    .message.content.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Errore";
        }
    }
}

