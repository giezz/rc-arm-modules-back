package ru.rightcode.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {
    public static final String API_V1 = "/api/v1";
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
