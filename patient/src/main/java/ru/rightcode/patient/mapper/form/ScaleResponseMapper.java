package ru.rightcode.patient.mapper.form;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.form.ScaleResponse;
import ru.rightcode.patient.model.Scale;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        })
public interface ScaleResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    ScaleResponse toResponse(Scale scale);
}
