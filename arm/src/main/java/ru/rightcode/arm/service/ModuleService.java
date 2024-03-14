package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.DoctorIdInfo;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.mapper.ModuleDetailsResponseMapper;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.repository.ModuleExerciseRepository;
import ru.rightcode.arm.repository.ModuleFormRepository;
import ru.rightcode.arm.repository.ModuleRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleService {

    private final ModuleExerciseRepository moduleExerciseRepository;
    private final ModuleFormRepository moduleFormRepository;
    private final ModuleRepository moduleRepository;

    private final RestrictionsService restrictionsService;
    private final DoctorService doctorService;

    private final ModuleDetailsResponseMapper moduleDetailsResponseMapper;

    public ModuleDetailsResponse getById(Long id) {
        Module module = moduleRepository.findByIdWithForms(id)
                .orElseThrow(() -> new EntityNotFoundException("net 1"));
        moduleRepository.findByIdWithExercises(id)
                .orElseThrow(() -> new EntityNotFoundException("net 2"));

        return moduleDetailsResponseMapper.map(module);
    }

    @Transactional
    public ModuleDetailsResponse renameModule(String doctorLogin, RenameModuleRequest request, Long moduleId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);
        module.setName(request.newName());

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse addExercise(String doctorLogin, AddModuleExerciseRequest request, Long moduleId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);
        ModuleExercise moduleExercise = new ModuleExercise();
        moduleExercise.setExerciseById(request.exerciseId());
        moduleExercise.setBlockById(request.blockId());
        module.addExercise(moduleExercise);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse deleteExercise(String doctorLogin, Long moduleId, Long moduleExerciseId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);
        ModuleExercise moduleExercise = moduleExerciseRepository.findById(moduleExerciseId).orElseThrow(EntityNotFoundException::new);
        module.deleteExercise(moduleExercise);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse addForm(String doctorLogin, AddModuleFormRequest request, Long moduleId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);
        ModuleForm moduleForm = new ModuleForm();
        moduleForm.setForm(new Form(request.formId()));
        module.addForm(moduleForm);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse deleteForm(String doctorLogin, Long moduleId, Long moduleFormId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);
        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId).orElseThrow(EntityNotFoundException::new);
        module.deleteForm(moduleForm);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    private Module getModuleIfDoctorCanEditModule(String doctorLogin, Long moduleId) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        Module module = moduleRepository.findByIdWithForms(moduleId).orElseThrow(EntityNotFoundException::new);
        moduleRepository.findByIdWithExercises(moduleId).orElseThrow(EntityNotFoundException::new);
        restrictionsService.canDoctorEditRehabProgram(doctor.getId(), module.getRehabProgram().getId());

        return module;
    }
}
