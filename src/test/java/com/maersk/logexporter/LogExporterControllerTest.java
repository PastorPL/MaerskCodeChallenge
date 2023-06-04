package com.maersk.logexporter;

import com.maersk.logexporter.model.LogLineDTO;
import com.maersk.logexporter.service.LogExporterService;
import com.maersk.logexporter.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LogExporterApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class LogExporterControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    LogExporterService logExporterService;

    private static final String DEFAULT_EXPORTER = "kafka";

    @Test
    public void Given_ClientWantsToStoreCorrectLogMessageWithDefaultExporter_WhenAllMandatoryFieldsArePopulated_ThenExportToDefaultChanelAndReturn204() throws Exception {
        final LogLineDTO dto =  TestUtil.createValidLogline();

        mvc.perform(
                post("/api/logs")
                    .contentType(APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dto))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(logExporterService, times(1)).export(dto);
        verify(logExporterService, times(0)).export(dto,"file");
    }

    @Test
    public void Given_ClientWantsToStoreCorrectLogMessageWithDefaultExporter_WhenAllMandatoryFieldsAreNotPopulated_ThenReturn400() throws Exception {
        final LogLineDTO dto =  TestUtil.createInvalidLogline();
        mvc.perform(
                        post("/api/logs")
                                .contentType(APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(dto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(logExporterService, times(0)).export(dto);
    }

    @Test
    public void Given_ClientWantsToStoreCorrectLogMessageWithNonDefaultExporter_WhenAllMandatoryFieldsArePopulated_ThenReturn204() throws Exception {
        final LogLineDTO dto =  TestUtil.createValidLogline();
        mvc.perform(post("/api/logs/kafka")
                     .contentType(APPLICATION_JSON)
                     .content(TestUtil.convertObjectToJsonString(dto))
                )
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(logExporterService, times(1)).export(dto,"kafka");
    }

}
