package ru.rightcode.arm.local.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.arm.service.FormResultService;

import java.util.List;

@SpringBootTest
public class FormResultServiceTest {

    @Autowired
    private FormResultService formResultService;
//
//    @Test
//    void test() {
//        formResultService.getResults(3L, 1L).forEach(System.out::println);
//    }
//
//    @Test
//    void test1() {
//        formResultService.getResults(3L).forEach(System.out::println);
//    }
//
//    @Test
//    void test2() {
//        formResultService.getResults(3L, List.of(1L, 2L, 3L)).forEach(System.out::println);
//    }
}
