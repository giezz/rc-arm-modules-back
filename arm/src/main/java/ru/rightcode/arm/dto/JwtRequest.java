package ru.rightcode.arm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequest {
    private final String username;
    private final String password;
}
