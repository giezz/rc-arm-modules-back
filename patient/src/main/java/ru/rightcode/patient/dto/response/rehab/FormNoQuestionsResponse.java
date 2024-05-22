package ru.rightcode.patient.dto.response.rehab;

import java.io.Serializable;

public record FormNoQuestionsResponse(
        Long id,
        String name,
        String description,
        String scale
) implements Serializable {
}
