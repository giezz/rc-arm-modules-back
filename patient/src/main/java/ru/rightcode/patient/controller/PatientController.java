package ru.rightcode.patient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    // TODO: метод для получения Доктора

    @GetMapping("/doctor")
    public ResponseEntity<?> getDoctor(Principal principal) {
        return ResponseEntity.ok(patientService.getDoctor(principal.getName()));
    }
    // TODO: метод для получения модулей
    // TODO: метод для получения истории реабилитации

}
