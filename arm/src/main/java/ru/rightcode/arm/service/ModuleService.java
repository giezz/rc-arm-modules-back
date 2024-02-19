package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.mapper.ModuleDetailsResponseMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.ModuleExercise;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.repository.ModuleExerciseRepository;
import ru.rightcode.arm.repository.ModuleFormRepository;
import ru.rightcode.arm.repository.ModuleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final RehabProgramService rehabProgramService;
    private final ModuleFormRepository moduleFormRepository;
    private final ModuleExerciseRepository moduleExerciseRepository;

    private final ModuleDetailsResponseMapper moduleDetailsResponseMapper;

    public ModuleDetailsResponse getById(Long id) {
        return moduleDetailsResponseMapper.map(
                moduleRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        );
    }
    
    /*
        FIXME:
            производительность запросов у обновлений/удалений?
     */
    @Transactional
    public ModuleDetailsResponse addForm(String doctorLogin, AddModuleFormRequest request, Long moduleId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);

        ModuleForm moduleForm = new ModuleForm();
        moduleForm.setFormById(request.formId());
        moduleForm.setBlockById(request.blockId());
        module.addForm(moduleForm);

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
    public ModuleDetailsResponse deleteForm(String doctorLogin, Long moduleId, Long moduleFormId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);

        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId).orElseThrow(EntityNotFoundException::new);
        module.deleteForm(moduleForm);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }

    @Transactional
    public ModuleDetailsResponse deleteExercise(String doctorLogin, Long moduleId, Long moduleExerciseId) {
        Module module = getModuleIfDoctorCanEditModule(doctorLogin, moduleId);

        ModuleExercise moduleExercise = moduleExerciseRepository.findById(moduleExerciseId).orElseThrow(EntityNotFoundException::new);
        module.deleteExercise(moduleExercise);

        return moduleDetailsResponseMapper.map(moduleRepository.save(module));
    }
    
    private Module getModuleIfDoctorCanEditModule(String doctorLogin, Long moduleId) {
        DoctorIdInfo doctor = rehabProgramService.getDoctorByLogin(doctorLogin);

        Module module = moduleRepository.findById(moduleId).orElseThrow(EntityNotFoundException::new);
        rehabProgramService.checkIfDoctorCanEdit(doctor.getId(), module.getRehabProgram().getId());

        return module;
    }
}
