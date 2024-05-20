package ru.rightcode.arm.service.medcard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ru.rightcode.arm.dto.response.ApiErrorResponse;

import java.util.function.Supplier;

@Service
@Slf4j
public class MedCardRequestHandlerService {

    public ResponseEntity<?> executeRequest(Supplier<ResponseEntity<?>> requestSupplier) {
        try {
            return requestSupplier.get();
        } catch (ResourceAccessException e) {
            log.warn("Сервис ЭМК недоступен");
            ApiErrorResponse response = new ApiErrorResponse(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Сервис ЭМК недоступен"
            );

            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
        }
    }
}
