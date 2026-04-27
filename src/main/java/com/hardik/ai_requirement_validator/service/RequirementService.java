package com.hardik.ai_requirement_validator.service;

import com.hardik.ai_requirement_validator.dto.RequirementRequest;
import com.hardik.ai_requirement_validator.dto.RequirementResponse;
import com.hardik.ai_requirement_validator.dto.RequirementSchemaDTO;
import com.hardik.ai_requirement_validator.exception.BusinessException;
import com.hardik.ai_requirement_validator.model.Requirement;
import com.hardik.ai_requirement_validator.repository.RequirementRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final AiSchemaExtractionService aiSchemaExtractionService;

    public RequirementResponse createRequirement(RequirementRequest requirementRequest){

        if(requirementRequest.getRequirementText().length() < 10){
            throw new BusinessException("Requirement text must contain at least 10 characters");
        }

        // 1️⃣ Save requirement
        Requirement requirement = Requirement.builder()
                .requirementText(requirementRequest.getRequirementText())
                .build();

        Requirement saved = requirementRepository.save(requirement);

        // 2️⃣ Extract schema using AI
        RequirementSchemaDTO schema =
                aiSchemaExtractionService.extractSchema(saved.getRequirementText());

        // 3️⃣ Return response
        return RequirementResponse.builder()
                .id(saved.getId())
                .requirementText(saved.getRequirementText())
                .createdAt(saved.getCreatedAt())
                .schema(schema)   // NEW
                .build();
    }
}