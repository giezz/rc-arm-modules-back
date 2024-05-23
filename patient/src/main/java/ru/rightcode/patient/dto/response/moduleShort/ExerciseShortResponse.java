package ru.rightcode.patient.dto.response.moduleShort;

import java.io.Serializable;

public record ExerciseShortResponse(
        Long id,
        String name,
        String exerciseType,
        String description,
        String videoUrl,
        String blockType
) implements Serializable {
}
