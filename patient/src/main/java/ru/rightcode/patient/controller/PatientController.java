package ru.rightcode.patient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.service.PatientService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/me")
    public ResponseEntity<?> getPatientData(Principal principal) {
        return ResponseEntity.ok(patientService.getYourSelf(principal.getName()));
    }

    // Метод для получения программы реабилитации
    @GetMapping("/rehab")
    public ResponseEntity<?> getRehabProgram(Principal principal) {
        return ResponseEntity.ok(patientService.getRehabProgram(principal.getName()));
    }

    // Метод для получения модулей
    @GetMapping("/modules/{moduleId}")
    public ResponseEntity<?> getModule(Principal principal,
                                        @PathVariable String moduleId) {
        return ResponseEntity.ok(patientService.getModule(
                principal.getName(), Long.parseLong(moduleId)));
    }
    // TODO: метод для получения истории реабилитации
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(Principal principal) {
        return ResponseEntity.ok(patientService.getHistory(principal.getName()));
    }

    @GetMapping("/modules/{moduleId}/exercises/{exerciseId}")
    public ResponseEntity<?> getExercise(Principal principal,
                                        @PathVariable String moduleId,
                                        @PathVariable String exerciseId) {
        return ResponseEntity.ok(patientService.getExerciseByModuleIdExerciseId(
                principal.getName(),
                Long.parseLong(moduleId),
                Long.parseLong(exerciseId))
        );
    }

    @GetMapping("/modules/{moduleId}/forms/{formId}")
    public ResponseEntity<?> getForm(Principal principal,
                                     @PathVariable String moduleId,
                                     @PathVariable String formId) {
        return ResponseEntity.ok(patientService.getFormByModuleIdFormId(
                principal.getName(), Long.parseLong(moduleId), Long.parseLong(formId)));
    }
}
