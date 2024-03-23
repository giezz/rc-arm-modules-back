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

    @GetMapping("/")
    public ResponseEntity<?> getByName(@RequestParam String name){
        return ResponseEntity.ok(formService.getFormByName(name));
    }

    // TODO: UPDATE FORM

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        formService.deleteForm(id);
        return ResponseEntity.ok("Deleted Form with id: "+ id);
    }
}
