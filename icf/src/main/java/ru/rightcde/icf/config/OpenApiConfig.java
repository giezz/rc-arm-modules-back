package ru.rightcde.icf.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        servers = @Server(url = "http://localhost:8082")
)
public class OpenApiConfig {
}
