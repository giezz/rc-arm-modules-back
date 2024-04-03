package ru.rightcode.anketi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rightcode.anketi.dto.auth.UserJwtRequest;
import ru.rightcode.anketi.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody UserJwtRequest jwtRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(jwtRequest));
    }
}
