package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.history.HistoryResponse;
import ru.rightcode.patient.model.RehabProgram;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
            HistoryProtocolMapper.class
        })
public interface HistoryResponseMapper {
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "protocols", source = "protocols")
    HistoryResponse toResponse(RehabProgram rehabProgram);
}
