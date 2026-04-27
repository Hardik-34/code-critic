package com.hardik.ai_requirement_validator.controller;

import com.hardik.ai_requirement_validator.common.RequirementApiResponse;
import com.hardik.ai_requirement_validator.dto.RequirementRequest;
import com.hardik.ai_requirement_validator.dto.RequirementResponse;
import com.hardik.ai_requirement_validator.model.Requirement;
import com.hardik.ai_requirement_validator.repository.RequirementRepository;
import com.hardik.ai_requirement_validator.service.RequirementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RequirementText")
public class RequirementController {
    private final RequirementService service;

        @PostMapping
        public RequirementApiResponse<RequirementResponse> create(
                @Valid @RequestBody RequirementRequest request) {

            RequirementResponse response= service.createRequirement(request);
            return RequirementApiResponse.<RequirementResponse>builder().isSuccess(true).message("Requirement created successfully").data(response).build();
    }
}