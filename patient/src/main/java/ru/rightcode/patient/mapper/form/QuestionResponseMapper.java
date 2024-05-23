package ru.rightcode.patient.mapper.form;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.form.QuestionResponse;
import ru.rightcode.patient.model.FormQuestion;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { VariantResponseMapper.class
        })
public interface QuestionResponseMapper {
    @Mapping(target = "id", source = "question.id")
    @Mapping(target = "content", source = "question.content")
    @Mapping(target = "type", source = "question.type")
    @Mapping(target = "required", source = "question.required")
    @Mapping(target = "variants", source = "question.variants")
    QuestionResponse toResponse(FormQuestion formQuestion);
}
