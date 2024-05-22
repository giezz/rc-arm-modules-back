package ru.rightcode.patient.dto.response.module;

import java.io.Serializable;

public record FormShortResponse(
        Long id,
        String name,
        String description
) implements Serializable {
}
