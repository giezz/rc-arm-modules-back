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

    @Mapping(target = "questionDto", source = "formQuestion.idQuestion")
    @Mapping(target = "formDto", source = "formQuestion.idForm")
    FormQuestionDto toDto(FormQuestion formQuestion);

    @Mapping(target = "idQuestion", source = "formQuestionDto.questionDto")
    @Mapping(target = "idForm", source = "formQuestionDto.formDto")
    FormQuestion toEntity(FormQuestionDto formQuestionDto);

    List<FormQuestionDto> toDtoList(List<FormQuestion> formQuestionList);
    List<FormQuestion> toEntityList(List<FormQuestionDto> formQuestionDtoList);
}