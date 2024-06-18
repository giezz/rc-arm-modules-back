package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.projection.DoctorInfo;
import ru.rightcode.arm.dto.request.JwtRequest;
import ru.rightcode.arm.dto.response.JwtResponse;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.util.JwtUtils;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final DoctorRepository doctorRepository;

    private final JwtUtils jwtUtils;

    public JwtResponse authenticate(JwtRequest authRequest) {
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

        return JwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails, doctor))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
