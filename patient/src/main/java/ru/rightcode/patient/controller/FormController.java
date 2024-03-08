package ru.rightcode.patient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.service.FormService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/forms")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping("/{formId}")
    @Tag(name = "form-controller", description = "Нет никаких проверок на соответсвие бизнес-логики!!!")
    public ResponseEntity<?> answerQuestions(@PathVariable Long formId,
                                             @RequestBody List<AnswerRequest> request,
                                             Principal principal) {
        final BigDecimal score = formService.submitAnswers(formId, request, principal.getName());
        return ResponseEntity.ok("score: " + score);
    }
}
