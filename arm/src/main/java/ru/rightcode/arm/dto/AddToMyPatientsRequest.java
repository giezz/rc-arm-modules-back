package ru.rightcode.arm.dto;

import lombok.Value;

@Value
public class AddToMyPatientsRequest {
    Long patientId;
    Long doctorId;
}
