package ru.rightcode.arm.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link ru.rightcode.arm.model.ModuleExercise}
 */
public record AddModuleExerciseRequest(
        Long exerciseId,
        Long blockId
) implements Serializable {
}