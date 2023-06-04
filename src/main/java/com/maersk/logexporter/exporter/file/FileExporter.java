package com.maersk.logexporter.exporter.file;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.model.LogLineDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileExporter implements Exporter {

    final String newLine = System.getProperty("line.separator");
    final String path;

    public FileExporter(String path) {
        this.path = path;
    }

    @Override
    public void export(LogLineDTO logLineDTO) {
        try {
            final File file = new File(this.path);
            FileUtils.writeStringToFile(file, logLineDTO.toString() + newLine, StandardCharsets.UTF_8, true);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
