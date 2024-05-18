package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record ModuleResponse(
        Long id,
        String name,
        Instant finishedAt
) implements Serializable {
}
