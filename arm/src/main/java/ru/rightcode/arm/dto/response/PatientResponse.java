package ru.rightcode.arm.dto.response;

import ru.rightcode.arm.model.Passport;
import ru.rightcode.arm.model.PatientStatus;

import java.io.Serializable;
import java.time.LocalDate;

public record PatientResponse(
        Long id,
        Long patientCode,
        String firstName,
        String middleName,
        String lastName,
        String gender,
        LocalDate birthDate,
        LocalDate deathDate,
        String address,
        String phoneNumber,
        String workPlaceData,
        String snils,
        String polis,
        PatientStatus patientStatus,
        Passport passport
) implements Serializable {
}
