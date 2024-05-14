package ru.rightcode.arm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArmApplication {

    public static final String MEDCART_API_URL = "http://localhost:8081/api/v1";

    public static void main(String[] args) {
        SpringApplication.run(ArmApplication.class, args);
    }
}
