package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record PassportResponse(
        String series,
        String number,
        Instant issuedDate,
        String issued
) implements Serializable {
}
