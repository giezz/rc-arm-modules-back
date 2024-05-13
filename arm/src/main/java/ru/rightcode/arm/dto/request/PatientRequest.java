package ru.rightcode.arm.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public record PatientRequest(
        String firstName,
        String middleName,
        String lastName,
        List<Integer> statuses,
        LocalDate birthDate,
        String gender
) implements Serializable {
}
