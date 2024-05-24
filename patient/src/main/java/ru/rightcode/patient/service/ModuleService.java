package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.form.FormResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.form.ModuleFormResponseMapper;
import ru.rightcode.patient.mapper.module.ModuleResponseMapper;
import ru.rightcode.patient.model.Module;
import ru.rightcode.patient.model.ModuleForm;
import ru.rightcode.patient.repository.ModuleFormRepository;
import ru.rightcode.patient.repository.ModuleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleService {
    private final ModuleRepository repository;
    private final ModuleFormRepository moduleFormRepository;
    private final ModuleResponseMapper moduleResponseMapper;

    private final ModuleFormResponseMapper moduleFormResponseMapper;

    @Transactional
    protected Module findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Module with id " + id + " not found"));
    }

    @Transactional
    protected ModuleForm getModuleById(Long moduleId, Long formsId) {
        return moduleFormRepository.getModuleFormByModuleIdAndId(moduleId, formsId)
                .orElseThrow(() -> new NotFoundException("Module with id " + moduleId + " not found"));
    }


    public FormResponse getModuleResponseById(Long moduleId, Long formsId) {
        ModuleForm module = getModuleById(moduleId, formsId);
        return moduleFormResponseMapper.toResponse(module);
    }
}
