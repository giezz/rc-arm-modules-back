package ru.rightcode.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.core.dto.AddToMyPatientsRequest;
import ru.rightcode.core.service.DoctorService;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    @PutMapping()
    public ResponseEntity<?> addToMyPatient(@RequestBody AddToMyPatientsRequest request) {

        doctorService.addPatient(request.getDoctorId(), request.getPatientId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyPatients() {

    }
}
