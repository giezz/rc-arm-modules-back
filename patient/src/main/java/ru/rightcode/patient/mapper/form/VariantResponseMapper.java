package ru.rightcode.patient.mapper.form;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.form.VariantResponse;
import ru.rightcode.patient.model.Variant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        })
public interface VariantResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "score", source = "score")
    VariantResponse toResponse(Variant variant);
}
