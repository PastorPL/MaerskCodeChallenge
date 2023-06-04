package com.maersk.logexporter.exporter;

import java.util.Collection;
import java.util.List;


public class Exporters {

    public static final String FILE = "file";
    public static final String KAFKA = "kafka";
    public static final String ACTIVE_MQ = "activeMq";
    public static final Collection<String> VALUES = List.of(new String[]{FILE, KAFKA, ACTIVE_MQ});

    public static boolean validateExporter(String exporter) {
        return VALUES.contains(exporter);
    }

}
