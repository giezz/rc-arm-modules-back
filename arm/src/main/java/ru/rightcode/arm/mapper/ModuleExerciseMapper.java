package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.model.ModuleExercise;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        ExerciseMapper.class
})
public interface ModuleExerciseMapper {

    @Mapping(target = "block.id", source = "blockId")
    ModuleExercise toEntity(ModuleExerciseDto moduleExerciseDto);

    List<ModuleExercise> toEntityList(List<ModuleExerciseDto> moduleExerciseDtos);

    @Mapping(target = "blockId", source = "block.id")
    ModuleExerciseDto toDto(ModuleExercise moduleExercise);

    List<ModuleExerciseDto> toDtoList(List<ModuleExercise> moduleExerciseList);

}