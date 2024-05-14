package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.service.HospitalizationHistoryService;
import ru.rightcode.arm.service.PatientService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final HospitalizationHistoryService hospitalizationHistoryService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String middleName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) List<Integer> status,
                                    @RequestParam(required = false) LocalDate birthDate,
                                    @RequestParam(required = false) String gender) {
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
                                gender
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

    @GetMapping("/{patientCode}/hosp-history")
    public ResponseEntity<?> getHospitalizationHistory(@PathVariable Long patientCode) {
        return ResponseEntity.ok(hospitalizationHistoryService.getPatientHospitalizationHistory(patientCode));
    }

    @GetMapping("/{patientCode}/hosp-history/{id}/epicrisises")
    public ResponseEntity<?> getEpicrisises(@PathVariable Long patientCode, @PathVariable Long id) {
        return ResponseEntity.ok(hospitalizationHistoryService.getEpicrisises(patientCode, id));
    }

}
