package ru.rightcode.arm.dto.response;

import java.time.LocalDate;

public record EpicrisisResponse(
        Long id,
        Long diagnosisCode,
        LocalDate creationDate,
        String epicrisisiData
) {
}
