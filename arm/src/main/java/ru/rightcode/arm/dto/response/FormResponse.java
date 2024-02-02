package ru.rightcode.arm.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link ru.rightcode.arm.model.Form}
 */

public record FormResponse(
        Long id,
        String name,
        String description
) implements Serializable {
}