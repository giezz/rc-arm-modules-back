package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.ExerciseResponse;
import ru.rightcode.arm.model.Exercise;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExerciseMapper {
    Exercise toEntity(ExerciseResponse exerciseResponse);

    @Mapping(target = "type", source = "exerciseType.name")
    ExerciseResponse toDto(Exercise exercise);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Exercise partialUpdate(ExerciseResponse exerciseResponse, @MappingTarget Exercise exercise);
}