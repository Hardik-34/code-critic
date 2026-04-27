package com.hardik.ai_requirement_validator.client.impl;

import com.hardik.ai_requirement_validator.client.OpenAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class openAiClientImpl implements OpenAiClient {
    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate=new RestTemplate();

    @Override
    public String callLLM(String prompt) {
        /*System.out.println("Using OpenAI key: " + apiKey.substring(0,10));
        String url="https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        Map<String,Object> request=new HashMap<>();
        request.put("model","gpt-4o-mini");
        List<Map<String,String>> messages= List.of(Map.of("role","user","content",prompt));
        request.put("messages",messages);
        HttpEntity<Map<String,Object>> entity=new HttpEntity<>(request,headers);
        ResponseEntity<String> response=restTemplate.postForEntity(url,entity,String.class);
        return response.getBody();*/
        return """
                {
                  "entities": ["User","Product"],
                  "features": ["login","product CRUD"],
                  "authentication": "JWT",
                  "database": "PostgreSQL",
                  "architecture": ["REST API"],
                  "externalServices": [],
                  "complexity": "low"
                }
                """;
    }
}
