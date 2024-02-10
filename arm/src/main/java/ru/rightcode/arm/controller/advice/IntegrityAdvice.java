package ru.rightcode.arm.controller.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rightcode.arm.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class IntegrityAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> integrityViolation() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                "Bad request"
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
