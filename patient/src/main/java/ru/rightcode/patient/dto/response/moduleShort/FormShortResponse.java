package ru.rightcode.patient.dto.response.moduleShort;

import java.io.Serializable;

public record FormShortResponse(
        Long moduleFormId,
        String name,
        String description
) implements Serializable {
}
