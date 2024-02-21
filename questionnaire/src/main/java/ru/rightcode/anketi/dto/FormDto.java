package ru.rightcode.anketi.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
public record FormDto(
        Long id,
        String name,
        String description,
        Long scaleId,
        List<QuestionDto> questions
) implements Serializable {
}