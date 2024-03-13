package ru.rightcode.arm.dto.response;

import java.io.Serializable;
import java.util.List;

public record FormWithResultsResponse(
        Long id,
        String name,
        String description,
        List<FormResultResponse> formResults
) implements Serializable {
}
