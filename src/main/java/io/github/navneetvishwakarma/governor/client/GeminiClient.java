package io.github.navneetvishwakarma.governor.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class GeminiClient {

    private final RestClient restClient;
    private final String apiKey;

    public GeminiClient(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.create();
    }

    public String generate(String prompt) {
        var request = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", prompt)
                ))
            )
        );

        var response = restClient.post()
            .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
            .header("Content-Type", "application/json")
            .body(request)
            .retrieve()
            .body(Map.class);

        // Extract text from response
        var candidates = (List<Map>) response.get("candidates");
        var content = (Map) candidates.get(0).get("content");
        var parts = (List<Map>) content.get("parts");
        return (String) parts.get(0).get("text");
    }
}