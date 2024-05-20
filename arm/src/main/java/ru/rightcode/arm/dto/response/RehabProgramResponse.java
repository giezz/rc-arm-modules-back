package ru.rightcode.arm.dto.response;

import ru.rightcode.arm.dto.ModuleDto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link ru.rightcode.arm.model.RehabProgram}
 */

public record RehabProgramResponse(
        Long id,
        PatientResponse patient,
        DoctorResponse doctor,
        Boolean isCurrent,
        Instant startDate,
        Instant endDate,
        List<ProgramFormResponse> forms,
        List<ModuleDto> modules
) implements Serializable {
}