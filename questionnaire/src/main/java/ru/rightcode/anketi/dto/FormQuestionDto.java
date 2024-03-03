package ru.rightcode.anketi.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FormQuestionDto {
    private Long id;
    private FormDto formDto;
    private QuestionDto questionDto;
    private Instant createdAt;

}
