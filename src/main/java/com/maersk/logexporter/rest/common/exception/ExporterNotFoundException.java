package com.maersk.logexporter.rest.common.exception;

public class ExporterNotFoundException extends RuntimeException {
    public ExporterNotFoundException(String message) {
        super(message);
    }
}
