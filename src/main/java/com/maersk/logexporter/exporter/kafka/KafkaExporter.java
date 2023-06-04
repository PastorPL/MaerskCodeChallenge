package com.maersk.logexporter.exporter.kafka;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.model.LogLineDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaExporter implements Exporter {

    private final String url;
    private final String topic;

    public KafkaExporter(String url, String topic) {
        this.url = url;
        this.topic = topic;
    }


    @Override
    public void export(LogLineDTO logLineDTO) {
        log.debug("Exporting to kafka. URL: {}, topic: {} ", this.url, this.topic);
    }
}
