package ru.rightcode.arm.dto;

import java.io.Serializable;
import java.time.Instant;

public record ModuleExerciseDto(
        Long id,
        Long exerciseId,
        String exerciseName,
        Long blockId,
        Instant finishedAt
) implements Serializable {
}
