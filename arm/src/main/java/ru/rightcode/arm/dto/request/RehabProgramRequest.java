package ru.rightcode.arm.dto.request;

import java.time.LocalDate;

public record RehabProgramRequest(
        String patientFirstName,
        String patientMiddleName,
        String patientLastName,
        LocalDate startDateFrom,
        LocalDate startDateTo,
        Boolean isCurrent
) {
}
