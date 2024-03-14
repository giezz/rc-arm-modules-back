package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {QuestionMapper.class,
        VariantMapper.class,
        FormQuestionMapper.class})
public interface FormMapper {
//    FormMapper INSTANCE = Mappers.getMapper(FormMapper.class);

    @Mapping(target = "questions", source = "questionList")
    @Mapping(source = "form.scale.id", target = "scaleId")
    FormDto toDto(Form form, List<Question> questionList);

    @Mapping(target = "scale.id", source = "scaleId")
    @Mapping(target = "formQuestions", source = "questions")
    Form toEntity(FormDto formDto);

}