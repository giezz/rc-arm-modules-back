package ru.rightcode.patient.mapper.rehab;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.ProgramFormResponse;
import ru.rightcode.patient.model.ProgramForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                FormNoQuestionsResponseMapper.class,
        })
public interface ProgramFormResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "description", source = "form.description")
    @Mapping(target = "finishedAt", source = "finishedAt")
    @Mapping(target = "typeName", source = "type.name")
    ProgramFormResponse toResponse(ProgramForm programForm);
}
