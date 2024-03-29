package ru.rightcode.arm.dto.projection;

/**
 * Projection for {@link ru.rightcode.arm.model.Doctor}
 */
public interface DoctorInfo {
    Long getId();
    String getFirstName();

    String getMiddleName();

    String getLastName();
}