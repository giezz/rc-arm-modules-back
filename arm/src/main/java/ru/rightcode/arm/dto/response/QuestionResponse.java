package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.util.List;

public record QuestionResponse(
        Long id,
        String content,
        List<VariantResponse> variants
) implements Serializable {
}
