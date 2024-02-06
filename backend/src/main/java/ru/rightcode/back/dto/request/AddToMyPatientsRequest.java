package ru.rightcode.back.dto.request;

import lombok.Value;

@Value
public class AddToMyPatientsRequest {
    Long patientId;
    Long doctorId;
}
