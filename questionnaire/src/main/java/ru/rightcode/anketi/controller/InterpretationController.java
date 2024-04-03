package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.service.InterpretationService;

@RestController
@RequestMapping("/interpretation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InterpretationController {
    private final InterpretationService interpretationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(interpretationService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(interpretationService.getAll());
    }
}
