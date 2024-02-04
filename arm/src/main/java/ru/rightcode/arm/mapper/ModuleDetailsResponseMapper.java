package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.dto.ModuleFormDto;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.model.Module;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModuleDetailsResponseMapper implements Mapper<Module, ModuleDetailsResponse> {

    private final ModuleExerciseDtoMapper moduleExerciseDtoMapper;

    private final ModuleFormDtoMapper moduleFormDtoMapper;
    @Override
    public ModuleDetailsResponse map(Module object) {
        List<ModuleExerciseDto> exercises = Optional.ofNullable(object.getExercises())
                .map(moduleExerciseDtoMapper::mapAll)
                .orElse(null);
        List<ModuleFormDto> forms = Optional.ofNullable(object.getForms())
                .map(moduleFormDtoMapper::mapAll)
                .orElse(null);

        return new ModuleDetailsResponse(
                object.getId(),
                object.getName(),
                object.getFinishedAt(),
                exercises,
                forms
        );
    }
}
