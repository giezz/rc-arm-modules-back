package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.service.DoctorService;
import ru.rightcode.arm.service.PatientService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
               patientService.getAll()
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code) {
        return ResponseEntity.ok(
                patientService.getByCode(code)
        );
    }

    @PatchMapping("/add-doctor/{patientId}")
    public ResponseEntity<?> addDoctor(@PathVariable Long patientId,
                                       Principal principal) {
        Doctor doctor = doctorService.getByLogin(principal.getName());
        patientService.addDoctor(patientId, doctor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
