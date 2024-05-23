package ru.rightcode.patient.mapper.form;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.form.FormResponse;
import ru.rightcode.patient.model.ModuleForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ScaleResponseMapper.class, QuestionResponseMapper.class,
        })
public interface FormResponseMapper {

    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "description", source = "form.description")
    @Mapping(target = "scale", source = "form.scale")
    @Mapping(target = "questions", source = "form.formQuestions")
    FormResponse toResponse(ModuleForm moduleForm);
}
