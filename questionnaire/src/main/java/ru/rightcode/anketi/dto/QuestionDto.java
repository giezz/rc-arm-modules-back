package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import ru.rightcode.anketi.model.QuestionTypeEnum;

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
    private QuestionTypeEnum type = QuestionTypeEnum.SINGLE_CHOICE;
    @Nullable
    private List<VariantDto> variants;
}
