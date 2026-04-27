package com.hardik.ai_requirement_validator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequirementRequest {
    @NotBlank(message = "This field cannot be empty")
    private String requirementText;

}
