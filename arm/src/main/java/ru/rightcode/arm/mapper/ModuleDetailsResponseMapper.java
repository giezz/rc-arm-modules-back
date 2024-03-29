package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.dto.response.ModuleFormResponse;
import ru.rightcode.arm.model.Module;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModuleDetailsResponseMapper implements Mapper<Module, ModuleDetailsResponse> {

    private final ModuleExerciseDtoMapper moduleExerciseDtoMapper;
    private final ModuleFormsResponseMapper moduleFormsResponseMapper;

    @Override
    public ModuleDetailsResponse map(Module object) {
        final List<ModuleExerciseDto> exercises = Optional.ofNullable(object.getExercises())
                .map(e -> e.stream().map(moduleExerciseDtoMapper::map).toList())
                .orElse(null);
        final List<ModuleFormResponse> forms = Optional.ofNullable(object.getForms())
                .map(f -> f.stream().map(moduleFormsResponseMapper::map).toList())
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
