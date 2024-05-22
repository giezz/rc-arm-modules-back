package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record ProgramFormResponse(
        Long id,
        FormNoQuestionsResponse form,
        Long typeId,
        Instant finishedAt
) implements Serializable {
}
