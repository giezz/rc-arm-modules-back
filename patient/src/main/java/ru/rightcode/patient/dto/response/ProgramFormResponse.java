package ru.rightcode.patient.dto.response;

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
