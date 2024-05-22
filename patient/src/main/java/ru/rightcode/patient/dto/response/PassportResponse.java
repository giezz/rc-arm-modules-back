package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public record PassportResponse(
        String series,
        String number,
        LocalDate issuedDate,
        String issued
) implements Serializable {
}
