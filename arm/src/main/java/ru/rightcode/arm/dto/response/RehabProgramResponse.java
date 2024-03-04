package ru.rightcode.arm.dto.response;

import ru.rightcode.arm.dto.ModuleDto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link ru.rightcode.arm.model.RehabProgram}
 */

public record RehabProgramResponse(
        Long id,
        Boolean isCurrent,
        Instant startDate,
        Instant endDate,
        FormResponse startForm,
        FormResponse endForm,
        List<ModuleDto> modules
) implements Serializable {
}