package ru.rightcode.arm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.AddModuleExerciseRequest;
import ru.rightcode.arm.dto.request.AddModuleFormRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ModuleServiceTest {

    @Autowired
    private ModuleService moduleService;

    @Test
    void getModuleById_test() {
        System.out.println(moduleService.getById(14L));
    }

    @Test
    void addExercise_test() {
        AddModuleExerciseRequest request = new AddModuleExerciseRequest(
                3L,
                1L
        );
        System.out.println(moduleService.addExercise("admin", request, 14L));
    }

    @Test
    void addForm_test() {
        AddModuleFormRequest request = new AddModuleFormRequest(
                2L,
                1L
        );
        System.out.println(moduleService.addForm("admin", request, 14L));
    }

    @Test
    void deleteForm_test() {
        System.out.println(
                moduleService.deleteForm("admin", 14L, 26L)
        );
    }

    @Test
    void deleteExercise_test() {
        System.out.println(
                moduleService.deleteForm("admin", 14L, 7L)
        );
    }
}