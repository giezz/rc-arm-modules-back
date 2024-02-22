package ru.rightcode.arm.dto.response;

import java.io.Serializable;

public record ExerciseResponse(
        Long id,
        String name,
        String videoUrl,
        String description,
        String type
) implements Serializable {
}
