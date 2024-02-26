package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QuestionDto{
    @Nullable
    private Long id ;
    @Nullable
    private String content;
    @Nullable
    private List<VariantDto> variants;
}
