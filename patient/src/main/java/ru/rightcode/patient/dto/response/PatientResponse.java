package ru.rightcode.patient.dto.response;

import ru.rightcode.patient.model.Passport;
import ru.rightcode.patient.model.PatientStatus;

import java.io.Serializable;
import java.time.LocalDate;

public record PatientResponse(
        String firstName,
        String middleName,
        String lastName,
        LocalDate birthDate,
        String address,
        String phoneNumber,
        String workPlaceData,
        String snils,
        String polis,

        PatientStatus patientStatus, // TODO: сделать ResponseEntity
        Passport passport // TODO: сделать ResponseEntity
) implements Serializable {
}
