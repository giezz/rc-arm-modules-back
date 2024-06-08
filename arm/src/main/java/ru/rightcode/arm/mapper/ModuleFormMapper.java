package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.ModuleFormResponse;
import ru.rightcode.arm.model.ModuleForm;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        FormMapper.class
})
public interface ModuleFormMapper {
    ModuleForm toEntity(ModuleFormResponse moduleFormResponse);

    List<ModuleForm> toEntityList(List<ModuleFormResponse> moduleFormResponses);

    ModuleFormResponse toDto(ModuleForm moduleForm);

    List<ModuleFormResponse> toDtoList(List<ModuleForm> moduleForms);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ModuleForm partialUpdate(ModuleFormResponse moduleFormResponse, @MappingTarget ModuleForm moduleForm);
}