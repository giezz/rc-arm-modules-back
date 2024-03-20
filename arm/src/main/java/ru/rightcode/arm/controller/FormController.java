package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.service.FormService;
import ru.rightcode.arm.service.ModuleFormService;

@RestController
@RequestMapping("api/v1/forms")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final ModuleFormService moduleFormService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(formService.getAll());
    }

    @GetMapping("/module-form/{id}/results")
    public ResponseEntity<?> getResults(@PathVariable Long id) {
        return ResponseEntity.ok(moduleFormService.getFormAndResults(id));
    }
}
