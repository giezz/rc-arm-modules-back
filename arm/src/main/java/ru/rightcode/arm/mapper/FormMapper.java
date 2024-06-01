package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.model.Form;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormMapper {
    @Mapping(source = "scaleName", target = "scale.name")
    Form toEntity(FormResponse formResponse);

    @Mapping(source = "scale.name", target = "scaleName")
    FormResponse toDto(Form form);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Form partialUpdate(FormResponse formResponse, @MappingTarget Form form);
}