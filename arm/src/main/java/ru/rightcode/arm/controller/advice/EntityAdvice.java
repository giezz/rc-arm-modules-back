package ru.rightcode.arm.controller.advice;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rightcode.arm.dto.response.ApiErrorResponse;
import ru.rightcode.arm.exceptions.PatientNotFoundException;

@RestControllerAdvice
public class EntityAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> entityNotFound(EntityNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                e.getMessage()
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(EntityExistsException.class)
    ResponseEntity<?> entityExists(EntityExistsException e) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                e.getMessage()
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    ResponseEntity<?> patientNotFound(PatientNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                e.getMessage()
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
