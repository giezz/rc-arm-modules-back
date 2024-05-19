package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.PassportResponse;
import ru.rightcode.patient.model.Passport;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Passport.class, PassportResponse.class
        })
public interface PassportMapper {
    @Mapping(target = "series", source = "series")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "issued", source = "issued")
    @Mapping(target = "issuedDate", source = "issuedDate")
    PassportResponse toResponse(Passport passport);
}
