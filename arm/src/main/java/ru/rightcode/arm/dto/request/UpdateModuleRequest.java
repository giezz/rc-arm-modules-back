package ru.rightcode.arm.dto.request;

import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.dto.response.ModuleFormResponse;

import java.util.List;

public record UpdateModuleRequest(
        List<ModuleExerciseDto> exercises,
        List<ModuleFormResponse> forms
) {
}
