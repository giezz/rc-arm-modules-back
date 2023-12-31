package ru.rightcode.arm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rightcode.arm.model.Passport;
import ru.rightcode.arm.model.PatientStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimplePatientResponse {
    private Long id;
    private Long patientCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String address;
    private String phoneNumber;
    private String workPlaceData;
    private String bookmark;
    private String snils;
    private String polis;
    private PatientStatus patientStatus;
    private Passport passport;
    private SimpleDoctorResponse doctor;
}
