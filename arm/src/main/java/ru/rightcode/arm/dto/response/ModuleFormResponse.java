package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record ModuleFormResponse(
        Long id,
        FormResponse form,
        Instant finishedAt,
        BigDecimal score
) implements Serializable {
}
