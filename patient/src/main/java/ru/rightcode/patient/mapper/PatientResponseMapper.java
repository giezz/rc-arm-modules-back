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

    @Mapping(source = "passport", target = "passport")
    @Mapping(source = "status", target = "patientStatus")
    PatientResponse toResponse(Patient patient);

    @Mapping(source = "passport", target = "passport")
    @Mapping(target = "status", source = "patientStatus")
    Patient toEntity(PatientResponse patientResponse);
}
