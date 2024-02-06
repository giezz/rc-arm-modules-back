package ru.rightcode.anketi.dto;

import lombok.*;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FormDto {
    private Long id;
    private String name;
    private String description;
    private Long scaleId;

    private List<Question> questions;
}
