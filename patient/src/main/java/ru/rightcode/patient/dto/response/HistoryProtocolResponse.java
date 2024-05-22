package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;

public record HistoryProtocolResponse(
        String scalesResult,
        String rehabResult,
        String recommendations,
        String rehabDiagnosis
) implements Serializable {
}
