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
    @Mapping(target = "type", source = "question.type")
    QuestionDto toDto(Question question);

    @Mapping(target = "formQuestions", ignore = true)
    @Mapping(target = "variants", source = "variants")
    @Mapping(target = "type", source = "type")
    Question toEntity(QuestionDto questionDto);
    List<QuestionDto> toDtoList(List<Question> questions);
    List<Question> toEntityList(List<QuestionDto> questionDtoList);
}