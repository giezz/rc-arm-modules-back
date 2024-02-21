package ru.rightcode.anketi.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FormQuestionDto {
    private Long id;
    private FormDto formDto;
    private List<QuestionDto> questionDtoList;
    private Instant createdAt;

}
