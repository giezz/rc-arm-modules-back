package ru.rightcode.anketi.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FormDto {
    private String name;
    private String description;
    private Long scaleId;
    private List<QuestionDto> questions;
}
