package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.FormResultResponse;
import ru.rightcode.arm.model.FormResult;

@Component
@RequiredArgsConstructor
public class FormResultResponseMapper implements Mapper<FormResult, FormResultResponse> {

    private final FormResponseMapper formResponseMapper;

    @Override
    public FormResultResponse map(FormResult object) {
        FormResponse formResponse = formResponseMapper.map(object.getForm());
        return new FormResultResponse(
                formResponse,
                object.getScore(),
                object.getCreationDate()
        );
    }
}
