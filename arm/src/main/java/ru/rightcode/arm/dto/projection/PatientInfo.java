package ru.rightcode.arm.dto.projection;

import ru.rightcode.arm.model.Patient;

import java.time.LocalDate;

/**
 * Projection for {@link Patient}
 */
public interface PatientInfo {
    Long getId();

    Long getPatientCode();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    String getGender();

    LocalDate getBirthDate();

    LocalDate getDeathDate();
}