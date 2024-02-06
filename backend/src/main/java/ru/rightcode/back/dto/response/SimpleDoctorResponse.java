package ru.rightcode.back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
