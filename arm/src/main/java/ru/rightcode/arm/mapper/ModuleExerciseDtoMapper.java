package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.model.ModuleExercise;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModuleExerciseDtoMapper implements Mapper<ModuleExercise, ModuleExerciseDto> {

    @Override
    public ModuleExerciseDto map(ModuleExercise object) {
        return new ModuleExerciseDto(
                object.getId(),
                object.getExercise().getId(),
                object.getExercise().getName(),
                "",
                object.getBlock().getId(),
                object.getFinishedAt()
        );
    }

    public List<ModuleExerciseDto> mapAll(Collection<ModuleExercise> moduleExercise) {
        return moduleExercise.stream().map(this::map).collect(Collectors.toList());
    }
}
