package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.service.FormService;
import ru.rightcode.arm.service.VariantService;

@RestController
@RequestMapping("api/v1/forms")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final VariantService variantService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(formService.getAll());
    }

    @GetMapping("/module-form/{id}/results")
    public ResponseEntity<?> getModuleFormResults(@PathVariable Long id) {
        return ResponseEntity.ok(variantService.getModuleFormAnsweredVariants(id));
    }

    @GetMapping("/program-form/{id}/results")
    public ResponseEntity<?> getProgramFormResults(@PathVariable Long id) {
        return ResponseEntity.ok(variantService.getProgramFormAnsweredVariants(id));
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(formService.getFormDetails(id));
    }
}
