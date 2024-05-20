package ru.rightcode.arm.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
public class ApiErrorResponse implements Serializable {
    int code;
    HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime timestamp;
    String message;

    public ApiErrorResponse(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = "Unhandled server error";
    }

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
