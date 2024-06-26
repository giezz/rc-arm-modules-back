package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.annotation.Loggable;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.service.PatientService;
import ru.rightcode.arm.service.medcard.MedCardHospitalizationHistoryService;
import ru.rightcode.arm.service.medcard.MedCardRehabilitationHistoryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Loggable
public class PatientController {

    private final PatientService patientService;
    private final MedCardHospitalizationHistoryService medCardHospitalizationHistoryService;
    private final MedCardRehabilitationHistoryService medCardRehabilitationHistoryService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String middleName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) List<Integer> status,
                                    @RequestParam(required = false) LocalDate birthDate,
                                    @RequestParam(required = false) String gender) {
        return ResponseEntity.ok(patientService.getAll(
                        pageNumber,
                        pageSize,
                        new PatientRequest(firstName, middleName, lastName, status, birthDate, gender)
                )
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code) {
        return ResponseEntity.ok(patientService.getByCode(code));
    }

    @GetMapping("/{patientCode}/rehab-programs")
    public ResponseEntity<?> getRehabPrograms(@PathVariable Long patientCode) {
        return ResponseEntity.ok(patientService.getAllRehabPrograms(patientCode));
    }

    @GetMapping("/{patientCode}/rehab-programs/{programId}")
    public ResponseEntity<?> getRehabPrograms(@PathVariable Long patientCode, @PathVariable Long programId) {
        return ResponseEntity.ok(patientService.getRehabProgram(patientCode,programId));
    }

    @GetMapping("/{patientCode}/rehab-programs/current")
    public ResponseEntity<?> getCurrentRehabProgram(@PathVariable Long patientCode) {
        return ResponseEntity.ok(patientService.getCurrentRehabProgram(patientCode));
    }

    @GetMapping("/{patientCode}/hosp-history")
    public ResponseEntity<?> getHospitalizationHistory(@PathVariable Long patientCode) {
        return medCardHospitalizationHistoryService.getPatientHospitalizationHistory(patientCode);
    }

    @GetMapping("/{patientCode}/hosp-history/{id}/epicrisises")
    public ResponseEntity<?> getEpicrisises(@PathVariable Long patientCode, @PathVariable Long id) {
        return medCardHospitalizationHistoryService.getEpicrisises(patientCode, id);
    }

    @GetMapping("/{patientCode}/rehab-history")
    public ResponseEntity<?> getPatientRehabHistory(@PathVariable Long patientCode) {
        return medCardRehabilitationHistoryService.getPatientRehabHistory(patientCode);
    }

}
