package ru.rightcode.patient.dto.request;

import java.io.Serializable;

public record PatientRequest(
        Long id
) implements Serializable {
}
