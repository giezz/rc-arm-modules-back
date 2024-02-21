package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VariantDto {
    @Nullable
    private Long id;
    @Nullable
    private String content;
    @Nullable
    private Double score;

}
