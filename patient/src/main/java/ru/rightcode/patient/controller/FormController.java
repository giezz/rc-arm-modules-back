package ru.rightcode.patient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.service.ModuleFormService;
import ru.rightcode.patient.service.PatientService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/patient/forms")
@RequiredArgsConstructor
public class FormController {

    private final ModuleFormService moduleFormService;
    private final PatientService patientService;

    @PostMapping("/{moduleFormId}")
    @Tag(name = "form-controller")
    public ResponseEntity<?> answerQuestions(@PathVariable Long moduleFormId,
                                             @RequestBody List<AnswerRequest> request,
                                             Principal principal) {
        PatientResponse pr = patientService.getYourSelf(principal.getName());
        final BigDecimal score = moduleFormService.submitModuleFormAnswer(moduleFormId, request);
        return ResponseEntity.ok("score: " + score);
    }
}
