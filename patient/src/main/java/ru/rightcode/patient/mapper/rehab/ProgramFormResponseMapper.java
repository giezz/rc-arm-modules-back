package ru.rightcode.patient.mapper.rehab;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.ProgramFormResponse;
import ru.rightcode.patient.model.ProgramForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProgramFormResponse.class,
                ProgramForm.class,
                FormNoQuestionsResponseMapper.class,
        })
public interface ProgramFormResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "typeId", source = "type.id")
    @Mapping(target = "form", source = "form")
    @Mapping(target = "finishedAt", source = "finishedAt")
    ProgramFormResponse toResponse(ProgramForm programForm);
}
