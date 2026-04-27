package com.hardik.ai_requirement_validator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequirementResponse {
    private Long id;
    private String requirementText;
    private LocalDateTime createdAt;
    private RequirementSchemaDTO schema;
}
