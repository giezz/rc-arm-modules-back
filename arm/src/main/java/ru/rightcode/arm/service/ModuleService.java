package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.RenameModuleRequest;
import ru.rightcode.arm.dto.request.UpdateModuleRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.exception.NoPermissionException;
import ru.rightcode.arm.mapper.ModuleDetailsMapper;
import ru.rightcode.arm.mapper.ModuleExerciseMapper;
import ru.rightcode.arm.mapper.ModuleFormMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.repository.ModuleRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleService {

    private final ModuleRepository moduleRepository;

    private final RestrictionsService restrictionsService;

    private final ModuleDetailsMapper moduleDetailsMapper;
    private final ModuleExerciseMapper moduleExerciseMapper;
    private final ModuleFormMapper moduleFormMapper;


    public ModuleDetailsResponse getById(Long id) {
        return moduleDetailsMapper.toDto(getModuleById(id));
    }

    @Transactional
    public ModuleDetailsResponse renameModule(String doctorLogin, RenameModuleRequest request, Long moduleId) {
        Module module = getOrThrowIfDoctorCantEdit(moduleId, doctorLogin);
        module.setName(request.newName());

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse updateModule(String login, Long id, UpdateModuleRequest request) {
        Module module = getOrThrowIfDoctorCantEdit(id, login);
        module.setExercises(moduleExerciseMapper.toEntityList(request.exercises()));
        module.setForms(moduleFormMapper.toEntityList(request.forms()));

        return moduleDetailsMapper.toDto(moduleRepository.save(module));
    }

    private Module getModuleById(Long id) {
        String errorMessage = "Модуль не найден";
        Module module = moduleRepository.findByIdWithForms(id)
                .orElseThrow(() -> new EntityNotFoundException(errorMessage));
        moduleRepository.findByIdWithExercises(id)
                .orElseThrow(() -> new EntityNotFoundException(errorMessage));

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
