package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.*;
import ru.rightcode.arm.service.ModuleFormService;
import ru.rightcode.arm.service.ProgramFormService;
import ru.rightcode.arm.service.RehabProgramService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/rehabs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RehabProgramController {

    private final RehabProgramService rehabProgramService;
    private final ModuleFormService moduleFormService;
    private final ProgramFormService programFormService;

    @GetMapping
    public ResponseEntity<?> getProgramsByCurrentDoctor(Principal principal,
                                                        RehabProgramRequest request) {
        return ResponseEntity.ok(rehabProgramService.getProgramsByCurrentDoctor(principal.getName(), request));
    }

    @GetMapping("/{id}/modules-forms-results")
    public ResponseEntity<?> getModulesResults(@PathVariable Long id,
                                               @RequestParam(required = false) List<Long> excludeIds) {
        if (excludeIds == null) {
            excludeIds = List.of(-1L);
        }
        return ResponseEntity.ok(moduleFormService.getAllResults(id, excludeIds));
    }

    @GetMapping("/{id}/program-forms-results")
    public ResponseEntity<?> getFormResults(@PathVariable Long id,
                                            @RequestParam(required = false) List<Long> excludeIds) {
        if (excludeIds == null) {
            excludeIds = List.of(-1L);
        }
        return ResponseEntity.ok(programFormService.getAllResults(id, excludeIds));
    }

    @PostMapping
    public ResponseEntity<?> create(Principal principal, @RequestBody CreateRehabProgramRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rehabProgramService.create(principal.getName(), request));
    }

    @PostMapping("/{id}/protocol")
    public ResponseEntity<?> createProtocol(Principal principal,
                                            @PathVariable Long id,
                                            @RequestBody CreateProtocolRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rehabProgramService.createProtocol(principal.getName(), id, request));
    }

    @GetMapping("/{id}/protocol")
    public ResponseEntity<?> getProtocol(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    @PutMapping("/{id}/form")
    public ResponseEntity<?> addForm(Principal principal,
                                     @RequestBody AddFormRequest request,
                                     @PathVariable Long id) {
        return ResponseEntity.ok(
                rehabProgramService.addForm(
                        principal.getName(),
                        request,
                        id
                )
        );
    }

    @PutMapping("/{id}/module")
    public ResponseEntity<?> addModule(Principal principal,
                                       @RequestBody AddModuleRequest request,
                                       @PathVariable Long id) {
        return ResponseEntity.ok(
                rehabProgramService.addModule(
                        principal.getName(),
                        request,
                        id
                )
        );
    }

}
