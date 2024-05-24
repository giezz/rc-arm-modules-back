package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.form.FormResponse;
import ru.rightcode.patient.mapper.form.QuestionResponseMapper;
import ru.rightcode.patient.mapper.form.ScaleResponseMapper;
import ru.rightcode.patient.model.ProgramForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { QuestionResponseMapper.class,
                ScaleResponseMapper.class
        })
public interface FormProgramResponseMapper {
    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "description", source = "form.description")
    @Mapping(target = "scale", source = "form.scale")
    @Mapping(target = "questions", source = "form.formQuestions")
    FormResponse toResponse(ProgramForm programForm);
}
