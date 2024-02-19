package ru.rightcode.anketi.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QuestionDto{
    private Long id;
    private String content;
    private List<VariantDto> variants;
}
