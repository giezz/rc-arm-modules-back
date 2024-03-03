package ru.rightcode.arm.integration.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.integration.IntegrationTestBase;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.repository.ModuleExerciseRepository;
import ru.rightcode.arm.repository.ModuleFormRepository;
import ru.rightcode.arm.repository.ModuleRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

public class ModuleRepositoryTest extends IntegrationTestBase {

    @Autowired
    private ModuleFormRepository moduleFormRepository;
    @Autowired
    private ModuleExerciseRepository moduleExerciseRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private RehabProgramRepository rehabProgramRepository;


    @Test
    @Transactional
    public void addFormTest() {
        Module module = moduleRepository.findById(1L).orElseThrow();

        ModuleForm moduleForm = new ModuleForm();
        moduleForm.setFormById(1L);
        moduleForm.setBlockById(1L);
        module.addForm(moduleForm);

        moduleRepository.save(module);
    }

}
