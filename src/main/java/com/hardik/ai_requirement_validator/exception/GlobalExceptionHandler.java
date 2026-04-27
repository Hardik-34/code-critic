package com.hardik.ai_requirement_validator.exception;

import com.hardik.ai_requirement_validator.common.RequirementApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequirementApiResponse<Object> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return RequirementApiResponse.builder()
                .isSuccess(false)
                .message(errorMessage)
                .data(null)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RequirementApiResponse<Object> handleGenericException(
            Exception ex) {

        return RequirementApiResponse.builder()
                .isSuccess(false)
                .message("Something went wrong")
                .data(null)
                .build();
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequirementApiResponse<Object> customException(BusinessException exception){
        return RequirementApiResponse.builder().isSuccess(false).message(exception.getMessage()).data(null).build();
    }

}
