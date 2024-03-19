package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.FormWithAnswersResponse;
import ru.rightcode.arm.dto.response.QuestionResponse;
import ru.rightcode.arm.model.Form;
import ru.rightcode.arm.model.FormQuestion;
import ru.rightcode.arm.model.ModuleForm;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FormWithAnswersResponseMapper implements Mapper<ModuleForm, FormWithAnswersResponse> {

    private final FormResponseMapper formResponseMapper;
    private final QuestionResponseMapper questionResponseMapper;
    @Override
    public FormWithAnswersResponse map(ModuleForm object) {
        final FormResponse formResponse = formResponseMapper.map(object.getForm());

        //TODO:
//        return new FormWithAnswersResponse(
//                formResponse,
//                questionResponses
//        );
        return null;
    }
}
