package ru.rightcode.arm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.rightcode.arm.dto.response.ModuleFormResultResponse;
import ru.rightcode.arm.model.ModuleForm;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        FormMapper.class
})
public interface ModuleFormResultMapper {

    @Mapping(target = "interpretation", source = "interpretation")
    @Mapping(target = "moduleName", source = "moduleForm.module.name")
    ModuleFormResultResponse toDto(ModuleForm moduleForm, String interpretation);
}
