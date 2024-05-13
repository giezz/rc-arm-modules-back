package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ExerciseResponse;
import ru.rightcode.arm.dto.response.PageableResponse;
import ru.rightcode.arm.mapper.ExerciseResponseMapper;
import ru.rightcode.arm.model.Exercise;
import ru.rightcode.arm.repository.ExerciseRepository;
import ru.rightcode.arm.utils.PageableUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseResponseMapper exerciseResponseMapper;

    private final PageableUtils pageableUtils;

    public PageableResponse<List<ExerciseResponse>> getAll(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Exercise> page = pageableUtils.getPageableResult(
                name,
                () -> exerciseRepository.findAll(pageable),
                () -> exerciseRepository.findAllByNameContainsIgnoreCase(name, pageable)
        );

        return new PageableResponse<>(
                page.get().map(exerciseResponseMapper::map).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}
