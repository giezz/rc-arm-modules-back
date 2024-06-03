package ru.rightcode.arm.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rightcode.arm.dto.response.ApiErrorResponse;
import ru.rightcode.arm.exception.NoPermissionException;

@RestControllerAdvice
public class SecurityAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<?> badCredentialsHandler(BadCredentialsException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                "Неверный логин или пароль"
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(NoPermissionException.class)
    ResponseEntity<?> noPermissionHandler(NoPermissionException e) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                e.getMessage()
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
