package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.FormResultResponse;
import ru.rightcode.arm.model.FormResult;

@Component
@RequiredArgsConstructor
public class FormResultResponseMapper implements Mapper<FormResult, FormResultResponse> {

    @Override
    public FormResultResponse map(FormResult object) {
        return new FormResultResponse(
                object.getId(),
                object.getScore(),
                object.getCreationDate()
        );
    }
}
