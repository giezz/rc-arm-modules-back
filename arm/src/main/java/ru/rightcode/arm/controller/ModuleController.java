package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.dto.request.ModuleRequest;
import ru.rightcode.arm.service.ModuleService;

@RestController
@RequestMapping("api/v1/module")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ModuleRequest request) {
        moduleService.create(request.getModuleName(), request.getRehabProgramId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
