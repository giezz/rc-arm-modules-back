package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.PatientStatus;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Patient.class, PatientStatus.class
        })
public interface RehabProgramMapper {


    RehabProgramResponse toRehabProgramResponse(Patient patient);
}
