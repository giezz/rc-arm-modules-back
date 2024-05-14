package ru.rightcode.medcart.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.medcart.service.epicrisis.EpicrisisServiceImpl;
import ru.rightcode.medcart.service.hospitalization.HospitalizationHistoryServiceImpl;

@RestController
@RequestMapping("api/v1/hosp-history")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HospitalisationHistoryController {

    private final HospitalizationHistoryServiceImpl hospitalizationHistoryService;
    private final EpicrisisServiceImpl epicrisisService;

    @GetMapping("/{patientCode}")
    public ResponseEntity<?> getPatientHospitalizationHistory(@PathVariable Long patientCode) {
        return ResponseEntity.ok(hospitalizationHistoryService.getHistory(patientCode));
    }

    @GetMapping("/{id}/epicrisises")
    public ResponseEntity<?> getEpicrisises(@PathVariable Long id) {
        return ResponseEntity.ok(epicrisisService.getEpicrisises(id));
    }
}
