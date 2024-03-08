package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ExerciseResponse;
import ru.rightcode.arm.mapper.ExerciseResponseMapper;
import ru.rightcode.arm.repository.ExerciseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseResponseMapper exerciseResponseMapper;

    public ExerciseResponse getById(Long id) {
        return exerciseResponseMapper.map(exerciseRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<ExerciseResponse> getAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(exerciseResponseMapper::map)
                .toList();
    }
}
