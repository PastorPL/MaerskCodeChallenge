package com.maersk.logexporter.rest.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiResponse
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private ErrorStatus status;
    private final LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(ErrorStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}
