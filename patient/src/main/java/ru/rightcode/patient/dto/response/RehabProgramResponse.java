package ru.rightcode.patient.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record RehabProgramResponse(
        Long id,
        Boolean isCurrent,
        Instant startDate,
        Instant endDate,
        List<ProgramFormResponse> forms,
        List<ModuleResponse> modules
) implements Serializable {
}
