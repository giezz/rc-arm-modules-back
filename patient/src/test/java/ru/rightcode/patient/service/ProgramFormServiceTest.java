package ru.rightcode.patient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rightcode.patient.dto.request.AnswerRequest;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ProgramFormServiceTest {

    @Autowired
    private ProgramFormService programFormService;

    @Test
    void completeForm() {
        List<AnswerRequest> request = List.of(
                new AnswerRequest(1L),
                new AnswerRequest(5L)
        );
        programFormService.submitProgramFormAnswer(1L, request, "patient");
        programFormService.submitProgramFormAnswer(2L, request, "patient");
    }
}
