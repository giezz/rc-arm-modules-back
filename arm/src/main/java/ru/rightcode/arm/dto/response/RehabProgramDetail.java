package ru.rightcode.arm.dto.response;

import ru.rightcode.arm.dto.ModuleDto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record RehabProgramDetail(
        Long id,
        Boolean isCurrent,
        Instant startDate,
        Instant endDate,
        List<ProgramFormResponse> forms,
        List<ModuleDto> modules
) implements Serializable {
}
