package ru.rightcode.anketi.mapper.mapstruct;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.FormQuestionDto;
import ru.rightcode.anketi.model.FormQuestion;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {FormMapper.class, QuestionMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FormQuestionMapper {

    @Mapping(target = "questionDto", source = "question")
    @Mapping(target = "formDto", source = "form")
    FormQuestionDto toDto(FormQuestion formQuestion);

    @Mapping(target = "question", source = "questionDto")
    @Mapping(target = "form", source = "formDto")
    FormQuestion toEntity(FormQuestionDto formQuestionDto);

}