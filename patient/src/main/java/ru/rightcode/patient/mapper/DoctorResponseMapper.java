package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.DoctorResponse;
import ru.rightcode.patient.model.Doctor;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Doctor.class, DoctorResponse.class
        })
public interface DoctorResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "middleName", source = "middleName")
    DoctorResponse toResponse(Doctor doctor);
}
