package ru.rightcode.anketi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDTO {
    private Long id;
    private String name;
    private String description;
    private Long scaleId;
}
