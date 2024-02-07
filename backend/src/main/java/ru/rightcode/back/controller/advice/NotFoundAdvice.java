package ru.rightcode.back.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rightcode.back.dto.response.ApiErrorResponse;

@ControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> tagNotFoundHandler(EntityNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiResponse = new ApiErrorResponse(
                httpStatus,
                e.getMessage()
        );
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
