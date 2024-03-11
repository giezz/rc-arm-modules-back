package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.service.FormService;

@RestController
@RequestMapping("api/v1/forms")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(formService.getAll());
    }
}
