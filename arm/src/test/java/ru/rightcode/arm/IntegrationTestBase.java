package ru.rightcode.arm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ConfigurationTest.class)
@ActiveProfiles(profiles = "test")
@WithMockUser(username = "admin", password = "pass", authorities = {"ADMIN"})
public abstract class IntegrationTestBase {

    @Autowired
    public ObjectMapper objectMapper;

    public String mapToJson(Object request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }
}
