package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.interpretation.ScaleInterpretationsResponse;
import ru.rightcode.anketi.model.Scale;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        })
public interface InterpretationMapper {
    @Mapping(source = "interpretations", target = "interpretations")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    ScaleInterpretationsResponse toResponse(Scale scale);

    @Mapping(source = "interpretations", target = "interpretations")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "forms", ignore = true)
    Scale toEntity(ScaleInterpretationsResponse scaleInterpretationsResponse);
}
