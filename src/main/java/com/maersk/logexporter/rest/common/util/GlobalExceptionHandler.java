package com.maersk.logexporter.rest.common.util;

import com.maersk.logexporter.rest.common.error.ApiError;
import com.maersk.logexporter.rest.common.error.ErrorStatus;
import com.maersk.logexporter.rest.common.exception.ExporterNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExporterNotFoundException.class)
    public final ResponseEntity<Object> handleExporterNotFoundException(Exception e) {
        ApiError apiError = new ApiError(ErrorStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
