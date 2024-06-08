package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.model.Form;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormMapper {

    @Mapping(target = "scale", ignore = true)
    Form toEntity(FormResponse formResponse);

    @Mapping(target = "scale", source = "scale.name")
    FormResponse toDto(Form form);
}