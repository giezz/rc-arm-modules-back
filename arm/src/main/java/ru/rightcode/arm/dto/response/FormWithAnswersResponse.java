package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.util.List;

public record FormWithAnswersResponse(
        FormResponse form,
        List<QuestionResponse> questions,
        List<VariantResponse> answeredVariants
) implements Serializable {
}
