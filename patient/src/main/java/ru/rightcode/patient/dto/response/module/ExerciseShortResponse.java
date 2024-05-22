package ru.rightcode.patient.dto.response.module;

import java.io.Serializable;

public record ExerciseShortResponse(
        String name,
        String exerciseType,
        String description,
        String videoUrl,
        String blockType
) implements Serializable {
}
