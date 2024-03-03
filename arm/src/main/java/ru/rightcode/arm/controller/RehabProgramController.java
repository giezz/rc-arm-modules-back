package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.AddFormRequest;
import ru.rightcode.arm.dto.request.AddModuleRequest;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;
import ru.rightcode.arm.service.RehabProgramService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/rehabs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RehabProgramController {

    private final RehabProgramService rehabProgramService;

    @GetMapping("/patient/{patientId}/current")
    public ResponseEntity<?> getCurrent(Principal principal, @PathVariable Long patientId) {
        return ResponseEntity.ok(rehabProgramService.getCurrent(principal.getName(), patientId));
    }

    @PostMapping
    public ResponseEntity<?> create(Principal principal, @RequestBody CreateRehabProgramRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rehabProgramService.create(principal.getName(), request));
    }

    @PutMapping("/{programId}/form")
    public ResponseEntity<?> addForm(Principal principal,
                                     @RequestBody AddFormRequest request,
                                     @PathVariable Long programId) {
        return ResponseEntity.ok(
                rehabProgramService.addForm(
                        principal.getName(),
                        request,
                        programId
                )
        );
    }

    @PutMapping("/{programId}/module")
    public ResponseEntity<?> addModule(Principal principal,
                                       @RequestBody AddModuleRequest request,
                                       @PathVariable Long programId) {
        return ResponseEntity.ok(
                rehabProgramService.addModule(
                        principal.getName(),
                        request,
                        programId
                )
        );
    }

}
