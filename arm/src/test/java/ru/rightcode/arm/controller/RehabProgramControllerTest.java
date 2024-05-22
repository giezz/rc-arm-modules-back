package ru.rightcode.arm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.IntegrationTestBase;
import ru.rightcode.arm.dto.request.*;
import ru.rightcode.arm.dto.response.PageableResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
class RehabProgramControllerTest extends IntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @Test
    void create() throws Exception {
        CreateRehabProgramRequest request = new CreateRehabProgramRequest(
                2L
        );
        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/rehabs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(request))
        ).andExpectAll(
                status().isCreated(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        ).andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );

        assertEquals(true, response.isCurrent());
    }

    @Test
    void getProgram() throws Exception {
        this.mockMvc.perform(get("/api/v1/rehabs/{id}", 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    @SuppressWarnings("unchecked")
    void getProgramsByCurrentDoctor() throws Exception {
        RehabProgramRequest request = new RehabProgramRequest(
                "ива",
                null,
                null,
                null,
                null,
                null
        );
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/rehabs")
                        .param("patientFirstName", request.patientFirstName())
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        PageableResponse<RehabProgramResponse> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                PageableResponse.class
        );
        assertEquals(response.pageNumber(), 0);
        assertEquals(response.pageSize(), 10);
    }

    @Test
    void createFinalProtocol() throws Exception {
        CreateProtocolRequest request = new CreateProtocolRequest(
                "",
                "",
                "Смерть",
                "",
                "",
                true
        );
        this.mockMvc.perform(post("/api/v1/rehabs/{id}/protocol", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/rehabs/{id}", 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );
        assertEquals(false, response.isCurrent());
    }

    @Test
    void getProtocols() throws Exception {
        this.mockMvc.perform(get("/api/v1/rehabs/{id}/protocols", 1))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void addForm() throws Exception {
        AddFormRequest request = new AddFormRequest(
                1L,
                AddFormRequest.FormType.START
        );
        MvcResult mvcResult = this.mockMvc.perform(put("/api/v1/rehabs/{id}/form", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                ).andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );

        assertEquals(3, response.forms().size());
        assertEquals(1, response.forms().get(2).typeId());
    }

    @Test
    void deleteForm() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(delete("/api/v1/rehabs/{programId}/form/{formId}", 1L, 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                ).andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );

        assertEquals(1, response.forms().size());
    }

    @Test
    void addModule() throws Exception {
        AddModuleRequest request = new AddModuleRequest(
                "Модуль"
        );
        MvcResult mvcResult = this.mockMvc.perform(put("/api/v1/rehabs/{id}/module", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                ).andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );

        assertEquals(2, response.modules().size());
    }

    @Test
    void deleteModule() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(delete("/api/v1/rehabs/{id}/module/{moduleId}", 1L, 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                ).andReturn();
        RehabProgramResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                RehabProgramResponse.class
        );

        assertEquals(0, response.modules().size());
    }
}