package ru.rightcode.arm.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ru.rightcode.arm.model.ModuleForm}
 */
public record ModuleFormDto(
        Long id,
        Long formId,
        Long blockId,
        Instant finishedAt
) implements Serializable {
}