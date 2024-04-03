package ru.rightcode.anketi.dto.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse implements Serializable {
    private final int code;
    private final HttpStatus httpStatus;
    //    private final BusinessCodeError businessCode;
//    private final String businessMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;
    private final String message;

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
