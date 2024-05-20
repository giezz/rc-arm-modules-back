package ru.rightcode.arm.dto;

import ru.rightcode.arm.dto.response.ExerciseResponse;

import java.io.Serializable;
import java.time.Instant;

public record ModuleExerciseDto(
        Long id,
        ExerciseResponse exercise,
        Long blockId,
        Instant finishedAt
) implements Serializable {
}
