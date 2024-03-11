package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record ProgramFormResponse(
        Long id,
        FormResponse form,
        Long typeId,
        Instant finishedAt,
        BigDecimal score
) implements Serializable {
}
