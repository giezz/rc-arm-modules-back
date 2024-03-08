package ru.rightcode.arm.dto;

import java.io.Serializable;
import java.time.Instant;

public record ModuleExerciseDto(
        Long id,
        Long exerciseId,
        String exerciseName,
        String thumbnailUrl,
        Long blockId,
        Instant finishedAt
) implements Serializable {
}
