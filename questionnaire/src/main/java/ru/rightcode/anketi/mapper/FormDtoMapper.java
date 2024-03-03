package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FormDtoMapper implements Mapper<FormDto, Form> {
    private final ScaleRepository scaleRepository;
    private final QuestionDtoMapper questionDtoMapper;
    private final Function<FormQuestion, QuestionDto> formQuestionToDtoMapper;

    @Override
    public Form toEntity(FormDto formDto) {
        if (formDto == null) {
            throw new IllegalArgumentException("FormDto cannot be null");
        }
        Scale scale = scaleRepository.findById(formDto.getScaleId()).orElse(null);
        if (scale == null) {
            throw new IllegalArgumentException("Scale with id " + formDto.getScaleId() + " not found");
        }
        return new Form(
                formDto.getName(),
                formDto.getDescription(),
                scale);
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
//        return null;
    }

    public FormDto toDto(Form form, List<Question> questionList){
        return FormDto.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale().getId())
                .questions(questionList.stream()
                        .map(q-> questionDtoMapper.toDto(q, q.getVariants()))
                        .collect(Collectors.toList())
                )
                .build();
    }
}
