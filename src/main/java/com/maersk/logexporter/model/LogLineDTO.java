package com.maersk.logexporter.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogLineDTO {
    @NotBlank
    private String applicationId;

    @NotBlank
    private String traceId;

    @NotBlank
    private String severity;

    @NotNull
    private final LocalDateTime timestamp;

    @NotBlank
    private String message;

    private String componentName;

    private String requestId;
}
