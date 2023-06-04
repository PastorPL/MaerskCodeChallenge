package com.maersk.logexporter.exporter.file;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.exporter.Exporters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
public class FileExporterConfig {


    private final String path;

    public FileExporterConfig(@Value("${exporter.file.path}") String path) {
        this.path = path;
    }

    @Bean(name=Exporters.FILE)
    public Exporter create() {
        return new FileExporter(this.path);
    }

}
