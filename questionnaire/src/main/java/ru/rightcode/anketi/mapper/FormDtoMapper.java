package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FormDtoMapper implements Mapper<FormDto, Form> {
    private final ScaleRepository scaleRepository;
    private final Function<FormQuestion, QuestionDto> formQuestionToDtoMapper;

    @Override
    public Form toEntity(FormDto formDto) {
        return Form.builder()
                .id(formDto.id())
                .name(formDto.name())
                .description(formDto.description())
                .scale(scaleRepository.findById(formDto.scaleId())
                        .orElseThrow(() ->
                                new NotFoundException("Scale not found with id: " + formDto.scaleId())
                        ))
                .build();
    }

    @Override
    public FormDto toDto(Form form){
        final List<QuestionDto> questionList = Optional.ofNullable(form.getFormQuestions())
                .map(formQuestions ->
                        formQuestions
                                .stream()
                                .map(formQuestionToDtoMapper)
                                .collect(Collectors.toList())
                )
                .orElse(null);
        return FormDto.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale().getId())
                .questions(questionList)
                .build();
    }
}
