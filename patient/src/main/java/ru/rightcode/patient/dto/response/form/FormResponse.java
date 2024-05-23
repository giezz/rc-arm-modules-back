package ru.rightcode.patient.dto.response.form;

import java.io.Serializable;

public record FormResponse(
        String name,
        String description,
        ScaleResponse scale,
        QuestionResponse[] questions
) implements Serializable {
}
