package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        ProgramFormMapper.class
})
public interface RehabProgramMapper {
    RehabProgram toEntity(RehabProgramResponse rehabProgramResponse);

    @AfterMapping
    default void linkModules(@MappingTarget RehabProgram rehabProgram) {
        rehabProgram.getModules().forEach(module -> module.setRehabProgram(rehabProgram));
    }

    @AfterMapping
    default void linkForms(@MappingTarget RehabProgram rehabProgram) {
        rehabProgram.getForms().forEach(form -> form.setRehabProgram(rehabProgram));
    }

    @Mapping(target = "patient.passport", ignore = true)
    RehabProgramResponse toDto(RehabProgram rehabProgram);

    @Mapping(target = "patient.passport", ignore = true)
    @Mapping(target = "modules", ignore = true)
    @Mapping(target = "forms", ignore = true)
    RehabProgramResponse toSimpleDto(RehabProgram rehabProgram);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RehabProgram partialUpdate(RehabProgramResponse rehabProgramResponse, @MappingTarget RehabProgram rehabProgram);
}