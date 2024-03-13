package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record FormResultResponse(
        Long id,
        BigDecimal score,
        Instant creationDate
) implements Serializable {
}
