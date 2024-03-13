package ru.rightcode.patient.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.patient.dto.PatientInfo;
import ru.rightcode.patient.dto.request.JwtRequest;
import ru.rightcode.patient.dto.response.JwtResponse;
import ru.rightcode.patient.repository.PatientRepository;
import ru.rightcode.patient.utils.JwtUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepository;
    private final JwtUtils jwtUtils;

    public JwtResponse authenticate(JwtRequest authRequest) {
        UserDetails userDetails;
        userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        ).getPrincipal();

        PatientInfo patient = patientRepository
                .findByUserLogin(userDetails.getUsername(), PatientInfo.class)
                .orElseThrow(EntityNotFoundException::new);

        return JwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails, patient))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
