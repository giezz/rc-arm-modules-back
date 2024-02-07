package ru.rightcode.back.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rightcode.back.dto.response.ApiErrorResponse;

@ControllerAdvice
public class BadCredentialsAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<Object> badCredentialsHandler(BadCredentialsException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                "Неверный логин или пароль"
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
