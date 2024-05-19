package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import ru.rightcode.anketi.model.QuestionTypeEnum;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QuestionDto implements Serializable {
    @Nullable
    private Long id ;
    @Nullable
    private String content;

    @Builder.Default
    private Boolean required = false;
    @Nullable
    @Builder.Default
    private QuestionTypeEnum type = QuestionTypeEnum.SINGLE_CHOICE;
    @Nullable
    private Set<VariantDto> variants;
}
