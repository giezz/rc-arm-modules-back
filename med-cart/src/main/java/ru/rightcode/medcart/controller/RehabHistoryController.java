package ru.rightcode.medcart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.medcart.dto.CreateRehabRecordRequest;
import ru.rightcode.medcart.service.hosphistory.RehabilitationHistoryService;

@RestController
@RequestMapping("api/v1/rehab-history")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RehabHistoryController {

    private final RehabilitationHistoryService rehabilitationHistoryService;

    @GetMapping("/{patientCode}")
    public ResponseEntity<?> getPatientHistory(@PathVariable Long patientCode) {
        return ResponseEntity.ok(rehabilitationHistoryService.getPatientHistory(patientCode));
    }

    @PostMapping
    public ResponseEntity<?> createPatientRehabHistoryRecord(@RequestBody CreateRehabRecordRequest request) {
        return ResponseEntity.ok(rehabilitationHistoryService.addRehabilitationRecord(request));
    }

}
