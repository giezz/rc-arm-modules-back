package ru.rightcode.arm.dto.response;

import java.io.Serializable;

public record VariantResponse(
        Long id,
        String content,
        boolean isAnswered
) implements Serializable {
}
