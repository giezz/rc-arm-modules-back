package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
