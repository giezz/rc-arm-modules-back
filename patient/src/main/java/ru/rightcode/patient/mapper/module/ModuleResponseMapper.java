package ru.rightcode.patient.mapper.module;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.moduleShort.ModuleResponse;
import ru.rightcode.patient.model.Module;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                FormShortResponseMapper.class,
                ExerciseShortResponseMapper.class,
        })
public interface ModuleResponseMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "finishedAt", target = "finishedAt")
    @Mapping(target = "exercises", source = "moduleExercises")
    @Mapping(target = "forms", source = "moduleForms")
    ModuleResponse toModuleResponse(Module module);
}
