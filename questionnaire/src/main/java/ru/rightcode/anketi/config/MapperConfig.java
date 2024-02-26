package ru.rightcode.anketi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.mapper.QuestionDtoMapper;
import ru.rightcode.anketi.model.FormQuestion;

import java.util.function.Function;

@Configuration
public class MapperConfig {

    @Bean
    public Function<FormQuestion, QuestionDto> formQuestionToDtoMapper(QuestionDtoMapper questionDtoMapper) {
        return formQuestion -> questionDtoMapper.toDto(formQuestion.getIdQuestion());
    }
}
