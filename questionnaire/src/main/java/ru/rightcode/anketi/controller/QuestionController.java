package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.anketi.service.question.QuestionServiceImpl;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {
    private final QuestionServiceImpl questionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(questionService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(questionService.getQuestions());
    }
}