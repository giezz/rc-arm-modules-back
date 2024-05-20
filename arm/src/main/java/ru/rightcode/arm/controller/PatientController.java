package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@Slf4j
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
        log.info("getAll");
        return ResponseEntity.ok(patientService.getAll(
                        pageNumber,
                        pageSize,
                        new PatientRequest(firstName, middleName, lastName, status, birthDate, gender)
                )
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code) {
        log.info("getByCode");
        return ResponseEntity.ok(patientService.getByCode(code));
    }

    @GetMapping("/{patientCode}/rehab-programs")
    public ResponseEntity<?> getRehabPrograms(@PathVariable Long patientCode) {
        log.info("getRehabPrograms");
        return ResponseEntity.ok(patientService.getAllRehabPrograms(patientCode));
    }

    @GetMapping("/{patientCode}/rehab-programs/{programId}")
    public ResponseEntity<?> getRehabPrograms(@PathVariable Long patientCode, @PathVariable Long programId) {
        log.info("getRehabPrograms");
        return ResponseEntity.ok(patientService.getRehabProgram(patientCode,programId));
    }

    @GetMapping("/{patientCode}/rehab-programs/current")
    public ResponseEntity<?> getCurrentRehabProgram(@PathVariable Long patientCode) {
        log.info("getCurrentRehabProgram");
        return ResponseEntity.ok(patientService.getCurrentRehabProgram(patientCode));
    }

    @GetMapping("/{patientCode}/hosp-history")
    public ResponseEntity<?> getHospitalizationHistory(@PathVariable Long patientCode) {
        log.info("getHospitalizationHistory");
        return medCardHospitalizationHistoryService.getPatientHospitalizationHistory(patientCode);
    }

    @GetMapping("/{patientCode}/hosp-history/{id}/epicrisises")
    public ResponseEntity<?> getEpicrisises(@PathVariable Long patientCode, @PathVariable Long id) {
        log.info("getEpicrisises");
        return medCardHospitalizationHistoryService.getEpicrisises(patientCode, id);
    }

    @GetMapping("/{patientCode}/rehab-history")
    public ResponseEntity<?> getPatientRehabHistory(@PathVariable Long patientCode) {
        log.info("getPatientRehabHistory");
        return medCardRehabilitationHistoryService.getPatientRehabHistory(patientCode);
    }

}
