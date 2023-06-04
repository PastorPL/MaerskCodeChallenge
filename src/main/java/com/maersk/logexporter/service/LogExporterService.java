package com.maersk.logexporter.service;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.exporter.Exporters;
import com.maersk.logexporter.model.LogLineDTO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogExporterService {


    private final BeanFactory beanFactory;
    private final String defaultExporter;

    public LogExporterService(BeanFactory beanFactory, @Value("${exporter.default}") String defaultExporter) {
        this.beanFactory = beanFactory;
        if(!Exporters.validateExporter(defaultExporter)) {
            throw new RuntimeException("Invalid default exporter value");
        }
        this.defaultExporter = defaultExporter;
    }

    private Exporter getDefaultExporter() {
        return this.getExporter(defaultExporter);
    }

    private Exporter getExporter(String exporter) {
        return this.beanFactory.getBean(exporter, Exporter.class);
    }

    public void export(LogLineDTO logLine) {
        this.getDefaultExporter().export(logLine);
    }

    public void export(LogLineDTO logLine, String exporter) {
        this.getExporter(exporter).export(logLine);
    }

}
