package ru.rightcode.anketi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.config.JwtUtils;
import ru.rightcode.anketi.dto.auth.DoctorInfo;
import ru.rightcode.anketi.dto.auth.UserJwtRequest;
import ru.rightcode.anketi.dto.auth.UserJwtResponse;
import ru.rightcode.anketi.repository.DoctorRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final DoctorRepository doctorRepository;

    private final JwtUtils jwtUtils;

    public UserJwtResponse authenticate(UserJwtRequest authRequest) {
        UserDetails userDetails;
        userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        ).getPrincipal();

        DoctorInfo doctor = doctorRepository
                .findByUserUsername(userDetails.getUsername(), DoctorInfo.class)
                .orElseThrow(EntityNotFoundException::new);

        return UserJwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails, doctor))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
