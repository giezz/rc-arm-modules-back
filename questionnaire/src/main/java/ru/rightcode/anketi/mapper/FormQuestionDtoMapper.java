package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.FormQuestionDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormQuestionDtoMapper implements Mapper<FormQuestionDto, FormQuestion> {
    private final FormDtoMapper formDtoMapper;
    private final QuestionDtoMapper questionDtoMapper;

    @Override
    public FormQuestion toEntity(FormQuestionDto object) {
        final Form idForm = Optional.ofNullable(object.getFormDto())
                .map(formDtoMapper::toEntity)
                .orElse(null);
        final Question questionList = Optional.ofNullable(object.getQuestionDto())
                .map(questionDtoMapper::toEntity)
                .orElse(null);

        return FormQuestion.builder()
                .id(object.getId())
                .idForm(idForm)
                .idQuestion(questionList)
                .build();
    }

    @Override
    public FormQuestionDto toDto(FormQuestion object) {
        return null;
    }

    public List<FormQuestion> mapToEntity(FormQuestionDto object){
        final Form idForm = Optional.ofNullable(object.getFormDto())
                .map(formDtoMapper::toEntity)
                .orElse(null);
        final Question questionList = Optional.ofNullable(object.getQuestionDto())
                .map(questionDtoMapper::toEntity)
                .orElse(null);
        List<FormQuestion> formQuestionList = new ArrayList<>();
        if (questionList == null){
            return formQuestionList;
        }
//        for (Question q: questionList){
            formQuestionList.add(
                    FormQuestion.builder()
                            .id(object.getId())
                            .idForm(idForm)
                            .idQuestion(questionList)
                            .createdAt(object.getCreatedAt())
                            .build()
            );
//        }

        return formQuestionList;
    }

    public List<QuestionDto> mapToQuestionDto(List<FormQuestion> formQuestion) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (FormQuestion fq : formQuestion) {
            questionDtoList.add(questionDtoMapper.toDto(fq.getIdQuestion()));
        }
        return questionDtoList;
    }
}
