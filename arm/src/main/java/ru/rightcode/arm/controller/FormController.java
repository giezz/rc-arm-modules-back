package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.arm.annotation.Loggable;
import ru.rightcode.arm.service.FormService;
import ru.rightcode.arm.service.VariantService;

@RestController
@RequestMapping("api/v1/forms")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Loggable
public class FormController {

    private final FormService formService;
    private final VariantService variantService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                    @RequestParam(required = false) String name) {
        return ResponseEntity.ok(formService.getAll(pageNumber, pageSize, name));
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
