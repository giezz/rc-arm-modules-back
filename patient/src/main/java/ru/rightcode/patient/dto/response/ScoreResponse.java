package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

public record ScoreResponse(
        BigDecimal score
) implements Serializable {
}
