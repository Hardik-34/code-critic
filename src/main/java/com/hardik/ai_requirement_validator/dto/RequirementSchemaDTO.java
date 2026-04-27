package com.hardik.ai_requirement_validator.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequirementSchemaDTO {
    private List<String> entities;
    private List<String> features;
    private String authentication;
    private String Database;
    private List<String> architecture;
    private List<String> externalServices;
    private String complexity;
}
