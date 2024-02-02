package ru.rightcode.arm.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link ru.rightcode.arm.model.ModuleExercise}
 */
public record ModuleExerciseRequest(
        Long exerciseId,
        Long exerciseExerciseTypeId,

        Long blockId
) implements Serializable {
}