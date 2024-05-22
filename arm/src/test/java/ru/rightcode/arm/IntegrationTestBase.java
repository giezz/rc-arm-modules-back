package ru.rightcode.arm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = ConfigurationTest.class)
@TestPropertySource(properties = {
        "spring.flyway.locations=classpath:db/testdata",
        "spring.jpa.show-sql=true",
        "jwt.lifetime-days=1",
        "jwt.secret=qwertyzxc",
        "jwt.security-develop-mode=true",
        "api.url.medcard.root.v1=http://localhost:8081/api/v1"
})
@WithMockUser(username = "admin", password = "pass", authorities = {"ADMIN"})
public abstract class IntegrationTestBase {

    @Autowired
    public ObjectMapper objectMapper;

    public String mapToJson(Object request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }
}
