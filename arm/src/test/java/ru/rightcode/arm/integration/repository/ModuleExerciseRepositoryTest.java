package ru.rightcode.arm.integration.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.arm.repository.ModuleExerciseRepository;

@SpringBootTest
public class ModuleExerciseRepositoryTest {

    @Autowired
    private ModuleExerciseRepository moduleExerciseRepository;
    @Test
    void checkRepository() {
        moduleExerciseRepository.findAll();
    }
}
