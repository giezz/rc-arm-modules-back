package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.FormWithResultsResponse;
import ru.rightcode.arm.mapper.FormResponseMapper;
import ru.rightcode.arm.mapper.FormWithResultsResponseMapper;
import ru.rightcode.arm.repository.FormRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FormService {

    private final FormRepository formRepository;

    private final FormResponseMapper formResponseMapper;
    private final FormWithResultsResponseMapper formWithResultsResponseMapper;

    public List<FormResponse> getAll() {
        return formRepository.findAll()
                .stream()
                .map(formResponseMapper::map)
                .toList();
    }

    public FormWithResultsResponse getFormResults(Long formId) {
        return formWithResultsResponseMapper.map(
                formRepository.findFormWithResults(formId).orElseThrow(EntityNotFoundException::new)
        );
    }

    public List<FormWithResultsResponse> getFormsResults(Long programId) {
        return formRepository.findFormsWithResultsByRehabProgramId(programId)
                .stream()
                .map(formWithResultsResponseMapper::map)
                .toList();
    }

}
