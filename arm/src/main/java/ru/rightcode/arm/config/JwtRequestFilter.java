package ru.rightcode.arm.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.rightcode.arm.service.UserService;
import ru.rightcode.arm.utils.JwtUtils;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // FIXME только для разработки
        if (authHeader.equals("Bearer admin")) {
            setAuthentication("admin", "eyJhbGciOiJIUzI1NiJ9.eyJkb2N0b3JJZCI6MSwicm9sZXMiOlsiQURNSU4iLCJET0NUT1IiXSwiZG9jdG9yRnVsbE5hbWUiOiLQkdCw0YLRg9GF0YLQuNC9INCc0LjRhdCw0LjQuyDQkNC70LXQutGB0LXQtdCy0LjRhyIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzA3MDY1Njc1LCJleHAiOjE3MDcxNTIwNzV9.9PphXNT0LJiU6ddcSRT9-wIKUFOvAhaMUDkbGu6gAjU");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            username = jwtUtils.extractUsername(jwt);
        } catch (ExpiredJwtException e) {
            log.debug("Время жизни токена вышло");
        } catch (SecurityException e) {
            log.debug("Подпись неправильная");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            setAuthentication(username, jwt);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String username, String jwt) {
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                jwtUtils.extractRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}