package ru.rightcode.patient.dto.response.moduleShort;

import java.io.Serializable;

public record FormShortResponse(
        Long id,
        String name,
        String description
) implements Serializable {
}
