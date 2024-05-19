package ru.rightcode.arm.dto.response;

import java.time.Instant;

public record ProtocolResponse(
        Long id,
        Instant creationDate,
        Boolean isFinal,
        String scalesResult,
        String rehabResult,
        String recommendations,
        String rehabDiagnosis
) {
}
