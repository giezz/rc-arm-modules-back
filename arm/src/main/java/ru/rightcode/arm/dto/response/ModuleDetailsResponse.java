package ru.rightcode.arm.dto.response;

import ru.rightcode.arm.dto.ModuleExerciseDto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record ModuleDetailsResponse(
        Long id,
        String name,
        Instant finishedAt,
        List<ModuleExerciseDto> exercises,
        List<ModuleFormResponse> forms
) implements Serializable {
}
