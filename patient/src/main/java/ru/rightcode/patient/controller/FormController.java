package ru.rightcode.patient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.service.ModuleFormService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/forms")
@RequiredArgsConstructor
public class FormController {

    private final ModuleFormService moduleFormService;

    @PostMapping("/{moduleFormId}")
    @Tag(name = "form-controller", description = "Нет никаких проверок на соответсвие бизнес-логики!!!")
    public ResponseEntity<?> answerQuestions(@PathVariable Long moduleFormId,
                                             @RequestBody List<AnswerRequest> request,
                                             Principal principal) {
        final BigDecimal score = moduleFormService.submitModuleFormAnswer(moduleFormId, request, principal.getName());
        return ResponseEntity.ok("score: " + score);
    }
}
