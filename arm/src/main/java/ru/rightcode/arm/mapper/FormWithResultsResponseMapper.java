package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResultResponse;
import ru.rightcode.arm.dto.response.FormWithResultsResponse;
import ru.rightcode.arm.model.Form;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormWithResultsResponseMapper implements Mapper<Form, FormWithResultsResponse> {

    private final FormResultResponseMapper formResultResponseMapper;
    @Override
    public FormWithResultsResponse map(Form object) {
        List<FormResultResponse> formResults = Optional.ofNullable(object.getResults())
                .map(results -> results.stream().map(formResultResponseMapper::map).toList())
                .orElse(null);

        return new FormWithResultsResponse(
                object.getId(),
                object.getName(),
                object.getDescription(),
                formResults
        );
    }
}
