package ru.rightcode.patient.dto.response;


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
        String bookmark,

        String patientStatus // TODO: сделать ResponseEntity
) implements Serializable {
}
