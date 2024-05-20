package ru.rightcode.arm.dto.response;

public record PageableResponse<T>(
        T content,
        int pageNumber,
        int pageSize,
        long totalElements
) {
}
