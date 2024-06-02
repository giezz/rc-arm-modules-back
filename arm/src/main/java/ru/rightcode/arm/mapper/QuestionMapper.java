package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.QuestionResponse;
import ru.rightcode.arm.model.Question;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    Question toEntity(QuestionResponse questionResponse);

    @AfterMapping
    default void linkVariants(@MappingTarget Question question) {
        question.getVariants().forEach(variant -> variant.setQuestion(question));
    }

    QuestionResponse toDto(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Question partialUpdate(QuestionResponse questionResponse, @MappingTarget Question question);
}