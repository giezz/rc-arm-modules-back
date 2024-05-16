package ru.rightcode.arm.dto.request;

import java.time.LocalDate;

public record CreateMedCardRehabRecordRequest(
        Long patientCode,
        Long muCode,
        Long doctorCode,
        LocalDate creationDate,
        String rehabResult
) {
}
