package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.util.List;

public record FormDetailsResponse(
        FormResponse form,
        List<QuestionResponse> questions
) implements Serializable {
}
