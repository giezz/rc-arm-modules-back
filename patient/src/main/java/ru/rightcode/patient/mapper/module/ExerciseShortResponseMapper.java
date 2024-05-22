package ru.rightcode.patient.mapper.module;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.module.ExerciseShortResponse;
import ru.rightcode.patient.model.ModuleExercise;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ExerciseShortResponse.class
        })
public interface ExerciseShortResponseMapper {
    @Mapping(target = "name", source = "exercise.name")
    @Mapping(target = "description", source = "exercise.description")
    @Mapping(target = "videoUrl", source = "exercise.videoUrl")
    @Mapping(target = "exerciseType", source = "exercise.exerciseType.name")
    @Mapping(target = "blockType", source = "block.name")
    ExerciseShortResponse toExerciseShortResponse(ModuleExercise moduleExercise);
}
