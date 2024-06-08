package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.PatientResponse;
import ru.rightcode.arm.model.Patient;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    Patient toEntity(PatientResponse patientResponse);

    PatientResponse toDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient partialUpdate(PatientResponse patientResponse, @MappingTarget Patient patient);
}