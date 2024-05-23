package ru.rightcode.patient.dto.response.form;

import java.io.Serializable;
import java.math.BigDecimal;

public record VariantResponse(
        Long id,
        String content,
        BigDecimal score
) implements Serializable {
}
