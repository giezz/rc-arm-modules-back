package ru.rightcode.patient.dto.request;

import java.io.Serializable;

public record AnswerRequest(
        Long variantId
) implements Serializable {
}
