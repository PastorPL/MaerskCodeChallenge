package com.maersk.logexporter;

import com.maersk.logexporter.exporter.Exporter;
import com.maersk.logexporter.exporter.file.FileExporter;
import com.maersk.logexporter.exporter.kafka.KafkaExporter;
import com.maersk.logexporter.model.LogLineDTO;
import com.maersk.logexporter.service.LogExporterService;
import com.maersk.logexporter.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LogExporterServiceTest {

    @Mock
    BeanFactory beanFactory;

    private static final String DEFAULT_EXPORTER = "kafka";

    LogExporterService logExporterService;

    @Mock
    KafkaExporter kafkaExporter;
    @Mock
    FileExporter fileExporter;

    @BeforeEach
    void beforeEach() {
        lenient().when(beanFactory.getBean(DEFAULT_EXPORTER, Exporter.class)).thenReturn(kafkaExporter);
        lenient().when(beanFactory.getBean("file", Exporter.class)).thenReturn(fileExporter);

        this.logExporterService = new LogExporterService(beanFactory, DEFAULT_EXPORTER);
    }

    @Test
    void useDefaultExporter() {
        LogLineDTO logLineDOT = TestUtil.createValidLogline();
        this.logExporterService.export(logLineDOT);
        verify(kafkaExporter, times(1)).export(logLineDOT);
    }

    @Test
    void useSpecificExporter() {
        LogLineDTO logLineDOT = TestUtil.createValidLogline();
        this.logExporterService.export(logLineDOT, "file");
        verify(fileExporter, times(1)).export(logLineDOT);
    }

    @Test
    void useDefaultExporterShouldHave0InteractionsWithOtherExporter() {
        LogLineDTO logLineDOT = TestUtil.createValidLogline();
        this.logExporterService.export(logLineDOT);
        verify(fileExporter, times(0)).export(logLineDOT);
    }



}
