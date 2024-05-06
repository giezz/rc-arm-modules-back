package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.service.PatientService;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/patients")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String middleName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String status,
                                    @RequestParam(required = false) LocalDate birthDate,
                                    @RequestParam(required = false) Boolean isDead) {
        return ResponseEntity.ok(
                patientService.getAll(
                        pageNumber,
                        pageSize,
                        new PatientRequest(
                                firstName,
                                middleName,
                                lastName,
                                status,
                                birthDate,
                                isDead
                        )
                )
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code) {
        return ResponseEntity.ok(patientService.getByCode(code));
    }

    @GetMapping("/{patientCode}/rehab-programs")
    public ResponseEntity<?> getRehabPrograms(@PathVariable Long patientCode,
                                              @RequestParam(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.ok(patientService.getAllRehabPrograms(patientCode));
        }
        return ResponseEntity.ok(patientService.getRehabProgram(patientCode, id));
    }

    @GetMapping("/{patientCode}/rehab-programs/current")
    public ResponseEntity<?> getCurrentRehabProgram(@PathVariable Long patientCode) {
        return ResponseEntity.ok(patientService.getCurrentRehabProgram(patientCode));
    }

}
