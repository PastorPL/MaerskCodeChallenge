package com.maersk.logexporter.rest;

import com.maersk.logexporter.exporter.Exporters;
import com.maersk.logexporter.model.LogLineDTO;
import com.maersk.logexporter.rest.common.exception.ExporterNotFoundException;
import com.maersk.logexporter.service.LogExporterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
@Slf4j
public class LogExporterController {

    final
    LogExporterService logExporterService;

    public LogExporterController(LogExporterService logExporterService) {
        this.logExporterService = logExporterService;
    }

    @Operation(summary = "Post new logline. Default exporter will be used")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Logline exported",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PostMapping()
    ResponseEntity<Void> addLog(@RequestBody @Valid LogLineDTO logLineDTO) {
        this.logExporterService.export(logLineDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Post new logline. Specified exporter will be used")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Logline exported",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PostMapping("/{exporter}")
    ResponseEntity<Void> addLogWithExporter(@PathVariable String exporter, @RequestBody @Valid LogLineDTO logLineDTO) {
        log.debug("In exporter handler");
        if(!Exporters.validateExporter(exporter)) {
            throw new ExporterNotFoundException(String.format("Exporter '%s' not found", exporter));
        } else {
            this.logExporterService.export(logLineDTO, exporter);
            return ResponseEntity.noContent().build();
        }
    }
}
