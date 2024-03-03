package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {VariantMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuestionMapper {
    @Mapping(target = "variants", source = "question.variants")
    QuestionDto toDto(Question question);

    @Mapping(target = "formQuestions", ignore = true)
    Question toEntity(QuestionDto questionDto);
    List<QuestionDto> toEntityList(List<Question> questions);
    List<Question> toDtoList(List<QuestionDto> questionDtoList);
}