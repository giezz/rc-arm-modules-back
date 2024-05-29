package ru.rightcode.patient.dto.request;

import io.micrometer.common.lang.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;

public record AnswerRequest(
        Long variantId,
        @Nullable BigDecimal scaleScore
) implements Serializable {
}
