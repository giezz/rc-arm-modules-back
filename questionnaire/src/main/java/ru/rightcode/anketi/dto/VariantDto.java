package ru.rightcode.anketi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VariantDto {
    private Long id;
    private String content;
    private Double score;

}
