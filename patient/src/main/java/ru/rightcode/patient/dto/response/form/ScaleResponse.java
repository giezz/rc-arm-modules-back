package ru.rightcode.patient.dto.response.form;

import java.io.Serializable;

public record ScaleResponse(
        Long id,
        String name,
        String description
) implements Serializable {
}
