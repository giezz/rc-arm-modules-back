package ru.rightcode.anketi.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FormDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private ScaleDto scaleId;
    private List<QuestionDto> questions;
}