package ru.rightcode.anketi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ScaleDto {
    private Long id;
    private String name;
    private String description;
}
