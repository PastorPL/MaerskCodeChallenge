package com.maersk.logexporter.exporter.kafka;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.exporter.Exporters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaExporterConfig {

    private final String url;
    private final String topic;

    public KafkaExporterConfig(@Value("${exporter.kafka.url}") String url, @Value("${exporter.kafka.topic}") String topic) {
        this.url = url;
        this.topic = topic;
    }

    @Bean(name=Exporters.KAFKA)
    public Exporter create() {
        return new KafkaExporter(url, topic);
    }
}
