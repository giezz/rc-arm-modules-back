package ru.rightcode.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.service.RehabProgramService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/rehabs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RehabProgramController {

    private final RehabProgramService rehabProgramService;

    @GetMapping("/{patientId}/current")
    public ResponseEntity<?> getCurrent(Principal principal,
                                        @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(
                rehabProgramService.getCurrent(principal.getName(), patientId)
        );
    }

    @PostMapping("create")
    public ResponseEntity<?> create(Principal principal,
                                    @RequestBody Patient patient) {
        rehabProgramService.create(principal.getName(), patient.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
