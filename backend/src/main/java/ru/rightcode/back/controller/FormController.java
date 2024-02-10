package ru.rightcode.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.back.model.Form;
import ru.rightcode.back.service.FormService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/form")
public class FormController {

    @Autowired
    private final FormService formService;

    /*@PostMapping("/create")
    public ResponseEntity<?> createForm(@RequestBody){
        return ResponseEntity.ok(formService.)
    }*/

    @GetMapping("/getByScale")
    public ResponseEntity<?> getByScaleIdForms(@RequestParam Long id){
        List<Form> formList = formService.findByScaleId(id);
        if (!formList.isEmpty()){
            return ResponseEntity.ok(formList);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getAllByName")
    public ResponseEntity<?> getAllByName(@RequestParam String name){
        return ResponseEntity.ok(formService.findByName(name));
    }
}
