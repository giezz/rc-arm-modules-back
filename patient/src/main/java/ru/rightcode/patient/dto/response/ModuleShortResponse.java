package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record ModuleShortResponse(
        Long id,
        String name,
        Instant finishedAt
) implements Serializable {
}
