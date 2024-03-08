package ru.rightcode.arm.integration.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rightcode.arm.integration.IntegrationTestBase;
import ru.rightcode.arm.repository.DoctorRepository;

public class DoctorRepositoryTest extends IntegrationTestBase {

    @Autowired
    private  DoctorRepository doctorRepository;

    @Test
    void getAllDoctors() {
        System.out.println(doctorRepository.findAll());
    }
}
