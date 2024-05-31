package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.FormProgramResponseMapper;
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
    private final FormProgramResponseMapper formProgramResponseMapper;

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


    public ModuleForm getModuleFormByModuleIdFormId(Long moduleId, Long formsId) {
        return getModuleById(moduleId, formsId);
    }
}
