package ru.rightcode.arm.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record ModuleDto(
        Long id,
        String name,
        Instant finishedAt
) implements Serializable {
}
