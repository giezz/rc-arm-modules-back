package ru.rightcode.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.core.dto.JwtRequest;
import ru.rightcode.core.dto.JwtResponse;
import ru.rightcode.core.utils.JwtUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public JwtResponse authenticate(JwtRequest authRequest) {
        UserDetails userDetails;
        userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        ).getPrincipal();

        return JwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
