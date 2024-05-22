package ru.rightcode.patient.mapper.rehab;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.FormNoQuestionsResponse;
import ru.rightcode.patient.model.Form;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        })
public interface FormNoQuestionsResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "scale", source = "scale.name")
    FormNoQuestionsResponse toFormNoQuestionsResponse(Form form);
}
