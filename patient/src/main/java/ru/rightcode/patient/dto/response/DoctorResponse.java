package ru.rightcode.patient.dto.response;

import java.io.Serializable;

public record DoctorResponse(
        String firstName,
        String middleName,
        String lastName,
        String phoneNumber
) implements Serializable {
}
