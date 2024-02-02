package ru.rightcode.arm.dto.request;

import java.io.Serializable;
import java.time.LocalDate;


public record PatientRequest(
        String firstName,
        String middleName,
        String lastName,
        String status,
        LocalDate birthDate,
        Boolean isDead
) implements Serializable {
}
