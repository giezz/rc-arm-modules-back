package ru.rightcode.arm.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtResponse {
    private final List<String> roles;
    private final String token;
}
