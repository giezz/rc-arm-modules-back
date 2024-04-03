package ru.rightcode.anketi.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class UserJwtResponse {
    private final List<String> roles;
    private final String token;
}
