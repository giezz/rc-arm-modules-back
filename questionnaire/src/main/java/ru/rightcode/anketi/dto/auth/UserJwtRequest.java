package ru.rightcode.anketi.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserJwtRequest implements Serializable {
    private final String username;
    private final String password;
}
