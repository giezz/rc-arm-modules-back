package ru.rightcode.arm.mapper;

import org.mapstruct.*;
import org.mapstruct.Mapper;
import ru.rightcode.arm.dto.response.VariantResponse;
import ru.rightcode.arm.model.Variant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VariantMapper {
    Variant toEntity(VariantResponse variantResponse);

    VariantResponse toDto(Variant variant);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Variant partialUpdate(VariantResponse variantResponse, @MappingTarget Variant variant);
}