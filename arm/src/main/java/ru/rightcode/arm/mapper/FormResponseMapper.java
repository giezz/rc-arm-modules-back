package ru.rightcode.arm.mapper;

import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.model.Form;

@Component
public class FormResponseMapper implements Mapper<Form, FormResponse> {
    @Override
    public FormResponse map(Form object) {
        return new FormResponse(
                object.getId(),
                object.getName(),
                object.getDescription()
        );
    }
}
