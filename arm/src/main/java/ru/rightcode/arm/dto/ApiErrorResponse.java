package ru.rightcode.arm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse {
    private final int code;
    private final HttpStatus httpStatus;
//    private final BusinessCodeError businessCode;
//    private final String businessMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;
    private final String message;

    public ApiErrorResponse(int code, HttpStatus httpStatus, LocalDateTime timestamp, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
//        this.businessMessage = businessMessage;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ApiErrorResponse(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
//        this.businessCode = businessCode;
        this.timestamp = LocalDateTime.now();
        this.message = "Unhandled server error";
//        this.businessMessage = businessCode.message();
    }

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
