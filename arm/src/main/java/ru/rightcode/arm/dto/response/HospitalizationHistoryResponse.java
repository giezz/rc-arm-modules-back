package ru.rightcode.arm.dto.response;

import java.time.LocalDate;

public record HospitalizationHistoryResponse(
        Long id,
        Long muCode,
        Long doctorCode,
        LocalDate startDate,
        LocalDate endDate,
        String hospData
) {
}
