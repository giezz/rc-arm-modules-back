package ru.rightcode.arm.dto;

import java.time.Instant;

/**
 * Projection for {@link ru.rightcode.arm.model.RehabProgram}
 */
public interface RehabProgramInfo {
    Long getId();

    Boolean getIsCurrent();

    Instant getCreatedAt();

    Instant getStartDate();

    Instant getEndDate();
}