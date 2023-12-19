package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.request.JwtRequest;
import ru.rightcode.arm.dto.response.JwtResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.utils.JwtUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final DoctorService doctorService;

    private final JwtUtils jwtUtils;

    public JwtResponse authenticate(JwtRequest authRequest) {
        UserDetails userDetails;
        userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        ).getPrincipal();

        Doctor doctor = doctorService.getByLogin(userDetails.getUsername());

        return JwtResponse.builder()
                .token(jwtUtils.generateToken(userDetails, doctor))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
