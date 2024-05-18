package ru.rightcode.patient.dto.response;

import ru.rightcode.patient.model.Passport;
import ru.rightcode.patient.model.PatientStatus;

import java.io.Serializable;
import java.time.LocalDate;

public record PatientResponse(
        String firstName,
        String middleName,
        String lastName,
        String gender,
        LocalDate birthDate,
        String address,
        String phoneNumber,
        String workPlaceData,
        String snils,
        String polis,
        String bookmark,

        String patientStatus, // TODO: сделать ResponseEntity
        PassportResponse passport // TODO: сделать ResponseEntity
) implements Serializable {
}
