package ru.rightcode.patient.dto.response.rehab;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record RehabProgramResponse(
        Long id,
        Boolean isCurrent,
        Instant startDate,
        Instant endDate,
        Set<ProgramFormResponse> programFormResponses,
        Set<ModuleShortResponse> moduleShortResponses
) implements Serializable {
}
