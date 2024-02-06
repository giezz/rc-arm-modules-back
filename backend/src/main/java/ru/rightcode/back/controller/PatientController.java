package ru.rightcode.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.back.dto.request.PatientRequest;
import ru.rightcode.back.service.PatientService;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String middleName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String status,
                                    @RequestParam(required = false) LocalDate birthDate,
                                    @RequestParam(required = false) Boolean isDead) {
        return ResponseEntity.ok(
                patientService.getAll(PatientRequest.builder()
                        .firstName(firstName)
                        .middleName(middleName)
                        .lastName(lastName)
                        .status(status)
                        .birthDate(birthDate)
                        .isDead(isDead)
                        .build())
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code) {
        return ResponseEntity.ok(
                patientService.getByCode(code)
        );
    }

    @PatchMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(Principal principal,
                                       @RequestBody Long patientId) {
        patientService.addDoctor(patientId, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/remove-doctor")
    public ResponseEntity<?> removeDoctor(@RequestBody Long patientId) {
        patientService.removeDoctor(patientId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
