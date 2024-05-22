package ru.rightcode.arm;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class ConfigurationTest {

    @Bean
    @ServiceConnection
    public static PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:14.5")
                .withUsername("postgres")
                .withPassword("admin");
    }

}
