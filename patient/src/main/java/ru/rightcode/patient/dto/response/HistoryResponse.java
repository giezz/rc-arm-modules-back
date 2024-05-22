package ru.rightcode.patient.dto.response;

import java.time.Instant;
import java.util.Set;

public record HistoryResponse(
        Instant startDate,
        Instant endDate,
        Set<HistoryProtocolResponse> protocols
) {
}
