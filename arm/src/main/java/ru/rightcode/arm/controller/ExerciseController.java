package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.annotation.Loggable;
import ru.rightcode.arm.service.ExerciseService;

@RestController
@RequestMapping("api/v1/exercises")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Loggable
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(required = false) String name) {
        return ResponseEntity.ok(exerciseService.getAll(pageNumber, pageSize, name));
    }
}
