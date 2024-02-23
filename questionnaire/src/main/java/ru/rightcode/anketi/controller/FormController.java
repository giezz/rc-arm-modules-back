package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.service.form.FormServiceImpl;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FormController {
    private final FormServiceImpl formService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(formService.getFormById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(formService.getAllForms());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForm(@RequestBody FormDto formDto){
        return ResponseEntity.ok(formService.createForm(formDto));
    }

    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam String name){
        return ResponseEntity.ok(formService.getFormByName(name));
    }

}
