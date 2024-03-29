package ru.rightcode.patient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rightcode.patient.dto.request.AnswerRequest;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ModuleFormServiceTest {

    @Autowired
    private ModuleFormService moduleFormService;

    @Test
    void completeForm() {
        List<AnswerRequest> request = List.of(
                new AnswerRequest(2L),
                new AnswerRequest(5L)
        );
        moduleFormService.submitModuleFormAnswer(1L, request, "patient");
    }
}
