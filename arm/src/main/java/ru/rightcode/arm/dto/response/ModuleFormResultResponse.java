package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record ModuleFormResultResponse(
        Long id,
        FormResponse form,
        String moduleName,
        Instant finishedAt,
        BigDecimal score,
        String interpretation
) implements Serializable {
}
