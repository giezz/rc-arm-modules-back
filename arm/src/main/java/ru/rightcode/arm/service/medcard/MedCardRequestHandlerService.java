package ru.rightcode.arm.service.medcard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ru.rightcode.arm.dto.response.ApiErrorResponse;

import java.util.function.Supplier;

@Service
public class MedCardRequestHandlerService {

    public ResponseEntity<?> executeRequest(Supplier<ResponseEntity<?>> requestSupplier) {
        try {
            return requestSupplier.get();
        } catch (ResourceAccessException e) {
            HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
            ApiErrorResponse response = new ApiErrorResponse(
                    status,
                    "Сервис ЭМК недоступен"
            );

            return ResponseEntity
                    .status(status)
                    .body(response);
        }
    }
}
