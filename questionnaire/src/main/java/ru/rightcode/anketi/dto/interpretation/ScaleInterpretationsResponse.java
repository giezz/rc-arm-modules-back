package ru.rightcode.anketi.dto.interpretation;

import jakarta.annotation.Nullable;
import ru.rightcode.anketi.model.Interpretation;

import java.io.Serializable;

public record ScaleInterpretationsResponse(
        @Nullable Long id,
        String name,
        String description,
        Interpretation[] interpretations
) implements Serializable {
}
