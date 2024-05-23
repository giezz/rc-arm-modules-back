package ru.rightcode.patient.dto.response.moduleShort;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record ModuleResponse(
        String name,
        Instant finishedAt,
        List<ExerciseShortResponse> exercises,
        List<FormShortResponse> forms
) implements Serializable {
}
