package ru.rightcode.arm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.rightcode.arm.dto.response.ModuleDetailsResponse;
import ru.rightcode.arm.model.Module;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        ModuleExerciseMapper.class,
        ModuleFormMapper.class

})
public interface ModuleDetailsMapper {

    Module toEntity(ModuleDetailsResponse moduleDetailsResponse);

    ModuleDetailsResponse toDto(Module module);

}
