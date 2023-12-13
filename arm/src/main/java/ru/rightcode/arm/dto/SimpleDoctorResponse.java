package ru.rightcode.arm.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleDoctorResponse {
    private Long id;
    private Long doctorCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
