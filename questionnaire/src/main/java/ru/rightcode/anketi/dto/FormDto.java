package ru.rightcode.anketi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDto {
    private Long id;
    private String name;
    private String description;
    private Long scaleId;
}
