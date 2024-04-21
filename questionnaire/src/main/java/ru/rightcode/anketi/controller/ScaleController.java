package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.dto.ScaleDto;
import ru.rightcode.anketi.service.ScaleService;

@RestController
@RequestMapping("api/v1/questionnaire/scale")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ScaleController {

    private final ScaleService scaleService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(scaleService.getScales());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addScale(ScaleDto scaleDto) {
        return ResponseEntity.ok(scaleService.addScale(scaleDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteScale(@PathVariable("id") Long id) {
        scaleService.deleteScale(id);
        return ResponseEntity.ok("Deleted Scale with id: "+ id);
    }
}
