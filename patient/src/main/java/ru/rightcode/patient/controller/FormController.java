package ru.rightcode.patient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.dto.response.ScoreResponse;
import ru.rightcode.patient.service.ModuleFormService;
import ru.rightcode.patient.service.PatientService;
import ru.rightcode.patient.service.ProgramFormService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/patient/forms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FormController {

    private final ModuleFormService moduleFormService;
    private final ProgramFormService programFormService;
    private final PatientService patientService;

    @PostMapping("/answers/modules/{moduleFormId}/submit")
    public ResponseEntity<?> answerModuleQuestions(Principal principal, @PathVariable String moduleFormId,
                                                   @RequestBody List<AnswerRequest> request
    ) {
        PatientResponse pr = patientService.getYourSelf(principal.getName());
        // TODO: проверить является ли Module пациента прикреплен к его программе
        final BigDecimal score = moduleFormService.submitModuleFormAnswer(Long.parseLong(moduleFormId), request);
        return ResponseEntity.ok(new ScoreResponse(score));
    }

    @PostMapping("/answers/programs/{programFormId}/submit")
    public ResponseEntity<?> answerProgramQuestions(Principal principal,
                                                    @PathVariable String programFormId,
                                                    @RequestBody List<AnswerRequest> request
    ) {
        PatientResponse pr = patientService.getYourSelf(principal.getName());
        // TODO: проверить является ли Module пациента прикреплен к его программе
        final BigDecimal score = programFormService.submitProgramFormAnswer(Long.parseLong(programFormId), request);
        return ResponseEntity.ok(new ScoreResponse(score));
    }

    @PostMapping("/answers/modules/{moduleFormId}")
    public ResponseEntity<?> getModuleAnswers(
            Principal principal,
            @PathVariable String moduleFormId)  {
        PatientResponse pr = patientService.getYourSelf(principal.getName());
        // TODO: проверить является ли Module пациента прикреплен к его программе
        return ResponseEntity.ok(moduleFormService.getAnsweredVariants(Long.parseLong(moduleFormId)));
    }

    @PostMapping("/answers/programs/{programFormId}")
    public ResponseEntity<?> getProgramAnswers(
            Principal principal,
            @PathVariable String programFormId)  {
        PatientResponse pr = patientService.getYourSelf(principal.getName());
        // TODO: проверить является ли Module пациента прикреплен к его программе
        return ResponseEntity.ok(programFormService.getAnsweredVariants(Long.parseLong(programFormId)));
    }
}
