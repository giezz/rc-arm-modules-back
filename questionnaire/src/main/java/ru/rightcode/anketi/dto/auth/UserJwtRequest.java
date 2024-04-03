package ru.rightcode.anketi.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJwtRequest {
    private final String username;
    private final String password;
}
