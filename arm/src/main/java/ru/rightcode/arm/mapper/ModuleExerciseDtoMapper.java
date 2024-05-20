package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.dto.response.ExerciseResponse;
import ru.rightcode.arm.model.ModuleExercise;

@Component
@RequiredArgsConstructor
public class ModuleExerciseDtoMapper implements Mapper<ModuleExercise, ModuleExerciseDto> {

    private final ExerciseResponseMapper exerciseResponseMapper;

    @Override
    public ModuleExerciseDto map(ModuleExercise object) {
        ExerciseResponse exerciseResponse = exerciseResponseMapper.map(object.getExercise());

        return new ModuleExerciseDto(
                object.getId(),
                exerciseResponse,
                object.getBlock().getId(),
                object.getFinishedAt()
        );
    }
}
