package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.model.ProgramForm;

import java.math.BigDecimal;
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

        Long type = null;
        BigDecimal score = null;
        if (object.getType() != null) {
            type = object.getType().getId();
        }
        if (object.getScore() != null) {
            score = object.getScore();
        }

        return new ProgramFormResponse(
                object.getId(),
                formResponse,
                type,
                object.getFinishedAt(),
                score
        );
    }
}
