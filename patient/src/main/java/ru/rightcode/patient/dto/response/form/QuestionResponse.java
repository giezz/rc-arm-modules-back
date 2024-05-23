package ru.rightcode.patient.dto.response.form;

import java.io.Serializable;

public record QuestionResponse(
        Long id,
        String content,
        Boolean required,
        QuestionTypeEnum type,
        VariantResponse[] variants
) implements Serializable {
}


