package ru.rightcode.core.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.core.repository.DoctorRepository;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void printDataTest() {
        System.out.println(doctorRepository.findAll());
    }
}
