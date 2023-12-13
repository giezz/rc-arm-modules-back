package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.service.PatientService;

@RestController
@RequestMapping("api/v1/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

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
}
