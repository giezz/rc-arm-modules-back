package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.service.FormService;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FormController {
    private final FormService formService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(formService.getFormDtoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(formService.getAllFormDto());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForm(@RequestBody FormDto formDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.createForm(formDto));
    }

    @GetMapping("/")
    public ResponseEntity<?> getByName(@RequestParam String name){
        return ResponseEntity.ok(formService.getListFormDtoByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        formService.deleteForm(id);
        return ResponseEntity.ok("Deleted Form with id: "+ id);
    }
}
