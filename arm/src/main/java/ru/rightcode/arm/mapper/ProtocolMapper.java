package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.ProtocolResponse;
import ru.rightcode.arm.model.Protocol;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProtocolMapper {
    Protocol toEntity(ProtocolResponse protocolResponse);

    ProtocolResponse toDto(Protocol protocol);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Protocol partialUpdate(ProtocolResponse protocolResponse, @MappingTarget Protocol protocol);
}