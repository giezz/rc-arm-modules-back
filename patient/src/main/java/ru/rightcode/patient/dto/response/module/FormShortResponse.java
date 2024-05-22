package ru.rightcode.patient.dto.response.module;

import java.io.Serializable;

public record FormShortResponse(
        String name,
        String description
) implements Serializable {
}
