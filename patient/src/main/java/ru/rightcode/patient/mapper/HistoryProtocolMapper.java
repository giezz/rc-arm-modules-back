package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.history.HistoryProtocolResponse;
import ru.rightcode.patient.model.Protocol;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        })
public interface HistoryProtocolMapper {
    @Mapping(target = "scalesResult", source = "scalesResult")
    @Mapping(target = "rehabResult", source = "rehabResult")
    @Mapping(target = "recommendations", source = "recommendations")
    @Mapping(target = "rehabDiagnosis", source = "rehabDiagnosis")
    HistoryProtocolResponse toResponse(Protocol protocol);
}
