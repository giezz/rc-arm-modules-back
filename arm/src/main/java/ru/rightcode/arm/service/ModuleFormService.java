package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ModuleFormResponse;
import ru.rightcode.arm.mapper.ModuleFormsResponseMapper;
import ru.rightcode.arm.repository.ModuleFormRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleFormService {

    private final ModuleFormRepository moduleFormRepository;
    private final ModuleFormsResponseMapper moduleFormsResponseMapper;

    public List<ModuleFormResponse> getResults(Long programId) {
        return moduleFormRepository.findFormResultsByRehabProgramId(programId)
                .stream()
                .map(moduleFormsResponseMapper::map)
                .toList();
    }
}
