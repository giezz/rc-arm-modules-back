package ru.rightcode.medcart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = @Server(url = "http://localhost:8081"))
public class OpenApiConfig {
}
