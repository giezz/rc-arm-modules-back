package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.moduleShort.ModuleResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.module.ModuleResponseMapper;
import ru.rightcode.patient.model.Module;
import ru.rightcode.patient.repository.ModuleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleService {
    private final ModuleRepository repository;
    private final ModuleResponseMapper moduleResponseMapper;

    @Transactional
    protected Module findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Module with id " + id + " not found"));
    }

    public ModuleResponse getModuleResponseById(Long moduleId) {
        return moduleResponseMapper.toModuleResponse(findById(moduleId));
    }
}
