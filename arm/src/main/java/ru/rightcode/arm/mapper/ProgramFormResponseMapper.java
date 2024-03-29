package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.model.ProgramForm;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProgramFormResponseMapper implements Mapper<ProgramForm, ProgramFormResponse> {

    private final FormResponseMapper formResponseMapper;

    @Override
    public ProgramFormResponse map(ProgramForm object) {
        final FormResponse formResponse = Optional.ofNullable(object.getForm())
                .map(formResponseMapper::map)
                .orElse(null);

        return new ProgramFormResponse(
                object.getId(),
                formResponse,
                object.getType().getId(),
                object.getFinishedAt()
        );
    }
}
