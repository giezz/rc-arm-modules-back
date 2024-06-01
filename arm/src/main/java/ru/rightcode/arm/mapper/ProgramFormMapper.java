package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.model.ProgramForm;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramFormMapper {
    ProgramForm toEntity(ProgramFormResponse programFormResponse);

    ProgramFormResponse toDto(ProgramForm programForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramForm partialUpdate(ProgramFormResponse programFormResponse, @MappingTarget ProgramForm programForm);
}