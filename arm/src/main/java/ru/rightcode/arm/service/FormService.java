package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.mapper.FormResponseMapper;
import ru.rightcode.arm.repository.FormRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FormService {

    private final FormRepository formRepository;

    private final FormResponseMapper formResponseMapper;

    public List<FormResponse> getAll() {
        return formRepository.findAll()
                .stream()
                .map(formResponseMapper::map)
                .collect(Collectors.toList());
    }

}
