package ru.rightcode.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.back.dto.request.JwtRequest;
import ru.rightcode.back.dto.response.JwtResponse;
import ru.rightcode.back.model.Doctor;
import ru.rightcode.back.model.Patient;
import ru.rightcode.back.repository.PatientRepository;
import ru.rightcode.back.utils.JwtUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final DoctorService doctorService;

    private final JwtUtils jwtUtils;
    private final PatientRepository patientRepository;

    public JwtResponse authenticate(JwtRequest authRequest) {
        UserDetails userDetails;
        userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        ).getPrincipal();

        Patient patient = patientRepository.getByLogin(userDetails.getUsername());

        return JwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails, patient))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
