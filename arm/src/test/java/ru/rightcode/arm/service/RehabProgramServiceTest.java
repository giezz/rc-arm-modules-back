package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.AddFormRequest;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RehabProgramServiceTest {

    @Autowired
    private RehabProgramService rehabProgramService;

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

        assertThrows(EntityNotFoundException.class, () -> {
            rehabProgramService.addForm("user", addFormRequest, 22L);
        });
    }

    @Test
    @Transactional
    void getCurrent_test() {
        System.out.println(rehabProgramService.getCurrent("admin", 1L));
    }
}