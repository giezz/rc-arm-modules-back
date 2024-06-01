package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.request.UpdateModuleRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.mapper.ModuleDetailsMapper;
import ru.rightcode.arm.mapper.ModuleExerciseMapper;
import ru.rightcode.arm.mapper.ModuleFormMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.repository.ExerciseRepository;
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

    private final ModuleDetailsMapper moduleDetailsMapper;
    private final ModuleExerciseMapper moduleExerciseMapper;
    private final ModuleFormMapper moduleFormMapper;

    private final ExerciseRepository exerciseRepository;

    public ModuleDetailsResponse getById(Long id) {
        return moduleDetailsMapper.toDto(getModuleById(id));
    }

    @Transactional
    public ModuleDetailsResponse renameModule(String doctorLogin, RenameModuleRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        module.setName(request.newName());

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Deprecated
    @Transactional
    public ModuleDetailsResponse addExercise(String doctorLogin, AddModuleExerciseRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        Exercise exercise = exerciseRepository.findById(request.exerciseId()).orElseThrow(EntityNotFoundException::new);
        module.addExercise(new ModuleExercise(exercise, new Block(request.blockId())));

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Deprecated
    @Transactional
    public ModuleDetailsResponse deleteExercise(String doctorLogin, Long moduleId, Long moduleExerciseId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleExercise moduleExercise = moduleExerciseRepository.findById(moduleExerciseId).orElseThrow(EntityNotFoundException::new);
        module.deleteExercise(moduleExercise);

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Deprecated
    @Transactional
    public ModuleDetailsResponse addForm(String doctorLogin, AddModuleFormRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleForm moduleForm = new ModuleForm();
        moduleForm.setForm(new Form(request.formId()));
        module.addForm(moduleForm);

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Deprecated
    @Transactional
    public ModuleDetailsResponse deleteForm(String doctorLogin, Long moduleId, Long moduleFormId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId).orElseThrow(EntityNotFoundException::new);
        module.deleteForm(moduleForm);

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Deprecated
    @Transactional
    public ModuleDetailsResponse updateModule(String login, Long id, UpdateModuleRequest request) {
        Module module = getOrThrowIfDoctorCantEdit(id, login);
        module.setExercises(moduleExerciseMapper.toEntityList(request.exercises()));
        module.setForms(moduleFormMapper.toEntityList(request.forms()));

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    private Module getModuleById(Long id) {
        Module module = moduleRepository.findByIdWithForms(id)
                .orElseThrow(() -> new EntityNotFoundException("Модуль не найден"));
        moduleRepository.findByIdWithExercises(id)
                .orElseThrow(() -> new EntityNotFoundException("Модуль не найден"));

        return module;
    }

    private Module getOrThrowIfDoctorCantEdit(Long id, String doctorLogin) {
        Module module = getModuleById(id);
        if (!restrictionsService.canDoctorEditRehabProgram(module.getRehabProgram(), doctorLogin)) {
            throw new NoPermissionException("Нет прав на редактирование программы реабилитации");
        }

        return module;
    }
}
