package com.hardik.ai_requirement_validator.service;

import com.hardik.ai_requirement_validator.dto.RequirementSchemaDTO;

public interface AiSchemaExtractionService {
    RequirementSchemaDTO extractSchema(String requirementText);
}
