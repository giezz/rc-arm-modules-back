package ru.rightcode.medcart.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateRehabRecordRequest {
    Long patientCode;
    Long muCode;
    Long doctorCode;
    LocalDate creationDate;
    String rehabResult;
}
