package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.ModuleResponse;
import ru.rightcode.patient.model.Module;
import ru.rightcode.patient.model.ModuleForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Module.class, ModuleResponse.class
        })
public interface ModuleMapper {

        @Mapping(target = "id", source = "id")
        @Mapping(target = "name", source = "name")
        @Mapping(target = "finishedAt", source = "finishedAt")
        ModuleResponse toResponse(Module module);

}
