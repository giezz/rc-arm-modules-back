package ru.rightcode.patient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.patient.dto.request.AnswerRequest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class FormSubmitTest {
    @Autowired
    private ModuleFormService moduleFormService;
    @Autowired
    private ProgramFormService programFormService;

    @Test
    public void submitModuleFormAnswers(){
        List<AnswerRequest> request = List.of(
                new AnswerRequest(35L, null),
                new AnswerRequest(37L, BigDecimal.valueOf(2.0))
        );
        moduleFormService.submitModuleFormAnswer(2L, request);
    }

    @Test
    public void submitProgramFormAnswers(){
        List<AnswerRequest> request = List.of(
                new AnswerRequest(35L, null),
                new AnswerRequest(37L, BigDecimal.valueOf(3.0))
        );
        programFormService.submitProgramFormAnswer(3L, request);
    }
}
