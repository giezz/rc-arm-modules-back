package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.dto.InterpretationDto;
import ru.rightcode.anketi.service.InterpretationService;

@RestController
@RequestMapping("api/v1/questionnaire/interpretation")
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

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String text) {
        return ResponseEntity.ok(interpretationService.search(text));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InterpretationDto interpretation) {
        return ResponseEntity.ok(interpretationService.create(interpretation));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody InterpretationDto interpretation) {
        return ResponseEntity.ok(interpretationService.update(id, interpretation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        interpretationService.delete(id);
        return ResponseEntity.ok("Deleted Interpretation with id: " + id);
    }
}
