package ru.rightcode.patient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rightcode.patient.dto.request.JwtRequest;
import ru.rightcode.patient.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/patient/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(jwtRequest));
    }
}
