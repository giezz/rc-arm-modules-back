package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rightcode.anketi.service.scale.ScaleServiceImpl;

@RestController
@RequestMapping("/scale")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ScaleController {
    private final ScaleServiceImpl scaleService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(scaleService.getScales());
    }
}
