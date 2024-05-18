package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.PatientStatus;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Patient.class, PatientStatus.class,
        })
public interface PatientResponseMapper{

    @Mapping(target = "patientStatus", source = "patientStatus")
    @Mapping(target = "passport", source = "passport")
    @Mapping(target = "snils", source = "snils")
    @Mapping(target = "polis", source = "polis")
    PatientResponse toResponse(Patient patient);

//    "id, gender, deathDate, bookmark, rehabPrograms".
    @Mapping(target = "snils", source = "snils")
    @Mapping(target = "polis", source = "polis")
    @Mapping(target = "bookmark", source = "bookmark")
    @Mapping(target = "rehabPrograms", ignore = true)
    @Mapping(target = "patientCode", ignore = true)
    @Mapping(target = "patientStatus", source = "patientStatus")
    @Mapping(target = "passport", source = "passport")
    Patient toEntity(PatientResponse patientResponse);
}
