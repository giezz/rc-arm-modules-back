package ru.rightcode.anketi.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.QuestionDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    QuestionDto toDto(Question question);
}