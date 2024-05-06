package ru.rightcode.arm.dto.response;

import java.io.Serializable;

public record DoctorResponse(
        Long id,
        String firstName,
        String middleName,
        String lastName
) implements Serializable {
}
