package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.annotation.Loggable;
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
@Loggable
public class RehabProgramController {

    private final RehabProgramService rehabProgramService;
    private final ModuleFormService moduleFormService;
    private final ProgramFormService programFormService;

    @GetMapping
    public ResponseEntity<?> getAllByCurrentDoctor(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                   @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                   Principal principal,
                                                   RehabProgramRequest request) {
        return ResponseEntity.ok(rehabProgramService.getProgramsByCurrentDoctor(
                pageNumber,
                pageSize,
                principal.getName(),
                request
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rehabProgramService.getProgram(id));
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

    @GetMapping("/{id}/protocols")
    public ResponseEntity<?> getProtocols(@PathVariable Long id) {
        return ResponseEntity.ok(rehabProgramService.getProtocols(id));
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

    @DeleteMapping("/{programId}/form/{formId}")
    public ResponseEntity<?> deleteForm(Principal principal,
                                        @PathVariable Long programId,
                                        @PathVariable Long formId) {
        return ResponseEntity.ok(
                rehabProgramService.deleteForm(
                        principal.getName(),
                        formId,
                        programId
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

    @DeleteMapping("/{programId}/module/{moduleId}")
    public ResponseEntity<?> deleteModule(Principal principal,
                                          @PathVariable Long programId,
                                          @PathVariable Long moduleId) {
        return ResponseEntity.ok(
                rehabProgramService.deleteModule(
                        principal.getName(),
                        moduleId,
                        programId
                )
        );
    }

}
