package ru.rightcode.arm.dto;

import java.io.Serializable;
import java.time.Instant;

public record ModuleExerciseDto(
        Long id,
        Long exerciseId,
        Long blockId,
        Instant finishedAt
) implements Serializable {
}
