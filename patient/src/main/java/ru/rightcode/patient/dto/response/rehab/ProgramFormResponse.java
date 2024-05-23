package ru.rightcode.patient.dto.response.rehab;

import java.io.Serializable;
import java.time.Instant;

public record ProgramFormResponse(
        Long id,
        String name,
        String description,
        String typeName,
        Instant finishedAt
) implements Serializable {
}
