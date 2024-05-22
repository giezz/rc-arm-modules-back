package ru.rightcode.arm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.IntegrationTestBase;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
class ModuleControllerTest extends IntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get("/api/v1/modules/{id}", 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void rename() throws Exception {
        RenameModuleRequest request = new RenameModuleRequest("name");
        MvcResult mvcResult = this.mockMvc.perform(patch("/api/v1/modules/{id}/name", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        ModuleDetailsResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ModuleDetailsResponse.class
        );

        assertEquals("name", response.name());
    }

    @Test
    void addForm() throws Exception {
        AddModuleFormRequest request = new AddModuleFormRequest(1L);
        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/modules/{id}/form", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        ModuleDetailsResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ModuleDetailsResponse.class
        );

        assertEquals(2, response.forms().size());
    }

    @Test
    void addExercise() throws Exception {
        AddModuleExerciseRequest request = new AddModuleExerciseRequest(1L, 1L);
        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/modules/{id}/exercise", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        ModuleDetailsResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ModuleDetailsResponse.class
        );

        assertEquals(4, response.exercises().size());
    }

    @Test
    void deleteForm() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(delete("/api/v1/modules/{id}/form/{moduleFormId}", 1L, 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        ModuleDetailsResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ModuleDetailsResponse.class
        );

        assertEquals(0, response.forms().size());
    }

    @Test
    void deleteExercise() throws Exception  {
        MvcResult mvcResult = this.mockMvc
                .perform(delete("/api/v1/modules/{id}/exercise/{moduleFormId}", 1L, 1L))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        ModuleDetailsResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ModuleDetailsResponse.class
        );

        assertEquals(2, response.exercises().size());
    }
}