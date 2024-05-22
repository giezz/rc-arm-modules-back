package ru.rightcode.arm.dto.response;

import java.util.List;

public record PageableResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements
) {
}
