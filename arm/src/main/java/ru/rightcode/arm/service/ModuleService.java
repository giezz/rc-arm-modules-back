package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.mapper.ModuleDetailsResponseMapper;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.model.Module;
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

    private final ModuleDetailsResponseMapper moduleDetailsResponseMapper;
    private final ExerciseRepository exerciseRepository;

    public ModuleDetailsResponse getById(Long id) {
        return moduleDetailsResponseMapper.map(getModuleById(id));
    }

    @Transactional
    public ModuleDetailsResponse renameModule(String doctorLogin, RenameModuleRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        module.setName(request.newName());

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse addExercise(String doctorLogin, AddModuleExerciseRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        Exercise exercise = exerciseRepository.findById(request.exerciseId()).orElseThrow(EntityNotFoundException::new);
        module.addExercise(new ModuleExercise(exercise, new Block(request.blockId())));

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse deleteExercise(String doctorLogin, Long moduleId, Long moduleExerciseId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleExercise moduleExercise = moduleExerciseRepository.findById(moduleExerciseId).orElseThrow(EntityNotFoundException::new);
        module.deleteExercise(moduleExercise);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse addForm(String doctorLogin, AddModuleFormRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleForm moduleForm = new ModuleForm();
        moduleForm.setForm(new Form(request.formId()));
        module.addForm(moduleForm);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse deleteForm(String doctorLogin, Long moduleId, Long moduleFormId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId).orElseThrow(EntityNotFoundException::new);
        module.deleteForm(moduleForm);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
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
