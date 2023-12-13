package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.service.DoctorService;
import ru.rightcode.arm.service.PatientService;
import ru.rightcode.arm.service.RehabProgramService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/rehab")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RehabProgramController {

    private final RehabProgramService rehabProgramService;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal,
                                 @RequestBody Patient patient) {
        Doctor doctor = doctorService.getByLogin(principal.getName());
        Patient patientFromDb = patientService.getById(patient.getId());

        return ResponseEntity.ok().body(
                rehabProgramService
        );
    }

    @PostMapping
    public ResponseEntity<?> create(Principal principal,
                                    @RequestBody Patient patient) {
        Doctor doctor = doctorService.getByLogin(principal.getName());
        Patient patientFromDb = patientService.getById(patient.getId());
        rehabProgramService.create(doctor, patientFromDb);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
