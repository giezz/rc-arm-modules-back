package ru.rightcode.core.dto;

import lombok.Value;

@Value
public class AddToMyPatientsRequest {
    Long patientId;
    Long doctorId;
}
