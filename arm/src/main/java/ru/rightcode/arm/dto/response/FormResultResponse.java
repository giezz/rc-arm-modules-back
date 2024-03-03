package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record FormResultResponse(
        FormResponse form,
        BigDecimal score,
        LocalDate creationDate
) implements Serializable {
}
