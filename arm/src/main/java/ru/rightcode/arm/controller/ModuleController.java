package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.annotation.Loggable;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.request.UpdateModuleRequest;
import ru.rightcode.arm.service.ModuleService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/modules")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Loggable
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody UpdateModuleRequest request,
                                    Principal principal) {
        return ResponseEntity.ok(moduleService.updateModule(principal.getName(), id, request));
    }
}
