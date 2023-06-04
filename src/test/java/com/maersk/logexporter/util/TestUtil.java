package com.maersk.logexporter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maersk.logexporter.model.LogLineDTO;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Utility class for testing REST controllers.
 */
public final class TestUtil {

    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static String convertObjectToJsonString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static LogLineDTO createInvalidLogline() {
        return LogLineDTO.builder()
                .traceId("SampleTraceId-01")
                .severity("ERROR")
                .message("Example LOG Message")
                .build();
    }

    public static LogLineDTO createValidLogline() {
        return LogLineDTO.builder()
                .applicationId("ID")
                .traceId("SampleTraceId-01")
                .severity("ERROR")
                .message("Example LOG Message")
                .timestamp(LocalDateTime.now())
                .build();
    }

}
