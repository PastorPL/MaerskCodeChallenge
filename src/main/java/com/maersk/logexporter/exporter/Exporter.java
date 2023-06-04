package com.maersk.logexporter.exporter;

import com.maersk.logexporter.model.LogLineDTO;

public interface Exporter {

    void export(LogLineDTO logLineDTO);
}
