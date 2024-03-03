package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.service.ModuleService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/modules")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getById(id));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<?> rename(@PathVariable Long id,
                                    @RequestBody RenameModuleRequest request,
                                    Principal principal) {
        return ResponseEntity.ok(moduleService.renameModule(principal.getName(), request, id));
    }

    @PostMapping("/{id}/form")
    public ResponseEntity<?> addForm(@PathVariable Long id,
                                     @RequestBody AddModuleFormRequest request,
                                     Principal principal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(moduleService.addForm(principal.getName(), request, id));
    }

    @PostMapping("/{id}/exercise")
    public ResponseEntity<?> addExercise(@PathVariable Long id,
                                         @RequestBody AddModuleExerciseRequest request,
                                         Principal principal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(moduleService.addExercise(principal.getName(), request, id));
    }

    @DeleteMapping("/{id}/form/{moduleFormId}")
    public ResponseEntity<?> deleteForm(@PathVariable("id") Long moduleId,
                                        @PathVariable Long moduleFormId,
                                        Principal principal) {
        return ResponseEntity
                .ok(moduleService.deleteForm(principal.getName(), moduleId, moduleFormId));
    }

    @DeleteMapping("/{id}/exercise/{moduleExerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable("id") Long moduleId,
                                            @PathVariable Long moduleExerciseId,
                                            Principal principal) {
        return ResponseEntity
                .ok(moduleService.deleteExercise(principal.getName(), moduleId, moduleExerciseId));
    }
}
