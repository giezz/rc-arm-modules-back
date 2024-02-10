package ru.rightcode.arm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.ModuleDto;
import ru.rightcode.arm.dto.request.AddFormRequest;
import ru.rightcode.arm.dto.request.AddModuleRequest;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RehabProgramServiceTest {

    @Autowired
    private RehabProgramService rehabProgramService;

    @Test
    @Transactional
    void getCurrent_test() {
        System.out.println(rehabProgramService.getCurrent("admin", 1L));
    }

    @Test
    @Transactional
    void createRehabProgram_test() {
        CreateRehabProgramRequest request = new CreateRehabProgramRequest(
                1L
        );
        System.out.println(
                rehabProgramService.create("admin", request)
        );
    }

    @Test
    @Transactional
    void updateRehabProgramWithWrongUser_test() {
        AddFormRequest addFormRequest = new AddFormRequest(2L, AddFormRequest.FormType.START);

        assertThrows(NoPermissionException.class, () -> {
            rehabProgramService.addForm("user", addFormRequest, 22222L);
        });
    }

    @Test
    @Transactional
    void addModule_test() {
        final String newModuleName = "superTestModule";
        AddModuleRequest request = new AddModuleRequest(
                newModuleName
        );
        RehabProgramResponse rehabProgramResponse = rehabProgramService
                .addModule("admin", request, 22L);

        final List<ModuleDto> modules = rehabProgramResponse.modules();
        ModuleDto moduleDto = modules.stream()
                .filter(m -> m.name().equals(newModuleName))
                .findFirst()
                .orElseThrow();

        System.out.println(moduleDto);

        assertEquals(newModuleName, moduleDto.name());
    }

}