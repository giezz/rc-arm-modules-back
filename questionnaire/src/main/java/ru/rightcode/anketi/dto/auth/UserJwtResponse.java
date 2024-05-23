package ru.rightcode.anketi.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@Builder
public class UserJwtResponse implements Serializable {
    private final List<String> roles;
    private final String token;
}
