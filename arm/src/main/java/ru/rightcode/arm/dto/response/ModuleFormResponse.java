package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record ModuleFormResponse(
        Long id,
        Long moduleId,
        FormResponse form,
        Instant finishedAt
) implements Serializable {
}
