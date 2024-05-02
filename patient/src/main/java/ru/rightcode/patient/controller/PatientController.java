package ru.rightcode.patient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rightcode.patient.service.PatientService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @RequestMapping("/me")
    public ResponseEntity<?> getPatient(Principal principal) {
        return ResponseEntity.ok(patientService.getYourSelf(principal.getName()));
    }
}
