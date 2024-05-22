package ru.rightcode.anketi.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InterpretationDto implements Serializable {
    @Nullable
    private Long id;
    @Nullable
    private String description;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private ScaleDto scale;

}
