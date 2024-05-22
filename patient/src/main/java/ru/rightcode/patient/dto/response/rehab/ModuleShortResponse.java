package ru.rightcode.patient.dto.response.rehab;

import java.io.Serializable;
import java.time.Instant;

public record ModuleShortResponse(
        Long id,
        String name,
        Instant finishedAt
) implements Serializable {
}
