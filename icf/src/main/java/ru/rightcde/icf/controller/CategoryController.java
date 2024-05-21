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
    public ResponseEntity<?> getAllRoots(@RequestParam(required = false) String q) {
        if (q == null || q.isEmpty()) {
            return ResponseEntity.ok(categoryService.getAllRoots());
        }
        return ResponseEntity.ok(categoryService.getByCode(q));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getCategoriesByParentCode(@PathVariable String code) {
        return ResponseEntity.ok(categoryService.getByParentCode(code));
    }
}
