package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VariantDto implements Serializable {
    @Nullable
    private Long id;
    @Nullable
    private String content;
    @Nullable
    private Double score;

}
