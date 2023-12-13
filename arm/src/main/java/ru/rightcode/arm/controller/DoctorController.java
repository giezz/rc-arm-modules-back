package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.AddToMyPatientsRequest;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.service.DoctorService;

import java.security.Principal;

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
    public ResponseEntity<?> getMyPatients(Principal principal) {
        Doctor doctor = doctorService.getByLogin(principal.getName());
        return ResponseEntity.ok(
                doctor.getPatients()
        );
    }
}
