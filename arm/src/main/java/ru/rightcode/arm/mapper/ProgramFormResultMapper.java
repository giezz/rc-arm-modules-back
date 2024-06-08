package ru.rightcode.arm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.rightcode.arm.dto.response.ModuleFormResultResponse;
import ru.rightcode.arm.dto.response.ProgramFormResultResponse;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.model.ProgramForm;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        FormMapper.class
})
public interface ProgramFormResultMapper {

    @Mapping(target = "interpretation", source = "interpretation")
    ProgramFormResultResponse toDto(ProgramForm programForm, String interpretation);
}
