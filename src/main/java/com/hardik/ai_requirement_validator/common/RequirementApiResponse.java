package com.hardik.ai_requirement_validator.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequirementApiResponse<T> {
    private boolean isSuccess;
    private String message;
    private T data;

}
