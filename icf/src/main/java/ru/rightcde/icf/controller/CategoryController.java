package ru.rightcde.icf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcde.icf.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getCategoriesByCode(@PathVariable String code) {
        return ResponseEntity.ok(categoryService.getByCode(code));
    }
}
