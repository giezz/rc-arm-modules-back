package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.PatientStatus;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Patient.class, PatientStatus.class
        })
public interface PatientResponseMapper{

    @Mapping(target = "patientStatus", source = "patientStatus.name")
    @Mapping(target = "bookmark", source = "bookmark")
    PatientResponse toResponse(Patient patient);
}
