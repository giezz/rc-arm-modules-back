package ru.rightcode.arm;

import org.springframework.boot.SpringApplication;

public class TestArmApplication {

    public static void main(String[] args) {
        SpringApplication.from(ArmApplication::main)
                .with(ConfigurationTest.class)
                .run(args);
    }
}
