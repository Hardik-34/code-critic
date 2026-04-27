package com.hardik.ai_requirement_validator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardik.ai_requirement_validator.client.OpenAiClient;
import com.hardik.ai_requirement_validator.dto.RequirementSchemaDTO;
import com.hardik.ai_requirement_validator.service.AiSchemaExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiSchemaExtractionService implements AiSchemaExtractionService {
    
    private final OpenAiClient openAiClient;
    private final ObjectMapper objectMapper;
    @Override
    public RequirementSchemaDTO extractSchema(String requirementText) {
        String prompt=buildPrompt(requirementText);
        String response=openAiClient.callLLM(prompt);
        String json=extractJson(response);
        try{
            return objectMapper.readValue(json, RequirementSchemaDTO.class);
        }
        catch(Exception e) {
            throw new RuntimeException("Failed to parse schema JSON", e);
        }
    }

    private String buildPrompt(String requirement) {

        return """
        You are a senior software architect.

        Extract a structured engineering schema from the requirement.

        Return ONLY JSON.

        Schema format:
        {
          "entities": [],
          "features": [],
          "authentication": "",
          "database": "",
          "architecture": [],
          "externalServices": [],
          "complexity": ""
        }

        Requirement:
        """ + requirement;
    }
    private String extractJson(String response){
        if(response==null){
            throw new RuntimeException("LLM response is null");
        }
        int start=response.indexOf("{");
        int end=response.lastIndexOf("}");
        if(start==-1||end==-1){
            throw new RuntimeException("Invalid JSON returned from LLM");
        }
        return response.substring(start,end+1);
    }
}
