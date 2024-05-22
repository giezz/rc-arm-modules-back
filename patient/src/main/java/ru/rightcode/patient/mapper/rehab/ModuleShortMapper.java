package ru.rightcode.patient.mapper.rehab;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.rehab.ModuleShortResponse;
import ru.rightcode.patient.mapper.module.ExerciseShortResponseMapper;
import ru.rightcode.patient.model.Module;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Module.class, ModuleShortResponse.class,
                ExerciseShortResponseMapper.class
        })
public interface ModuleShortMapper {
        @Mapping(target = "id", source = "id")
        @Mapping(target = "name", source = "name")
        @Mapping(target = "finishedAt", source = "finishedAt")
        ModuleShortResponse toResponse(Module module);
}
