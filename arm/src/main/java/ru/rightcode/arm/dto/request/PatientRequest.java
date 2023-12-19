package ru.rightcode.arm.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PatientRequest {
    String firstName;
    String middleName;
    String lastName;
    String status;
    LocalDate birthDate;
    Boolean isDead;
}
