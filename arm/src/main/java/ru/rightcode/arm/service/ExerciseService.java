package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.model.Exercise;
import ru.rightcode.arm.repository.ExerciseRepository;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise getById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
