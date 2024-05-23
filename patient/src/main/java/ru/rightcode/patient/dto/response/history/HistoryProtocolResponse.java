package ru.rightcode.patient.dto.response.history;

import java.io.Serializable;

public record HistoryProtocolResponse(
        String scalesResult,
        String rehabResult,
        String recommendations,
        String rehabDiagnosis
) implements Serializable {
}
