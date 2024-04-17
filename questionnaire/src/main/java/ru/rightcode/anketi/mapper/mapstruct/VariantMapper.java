package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.model.Variant;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VariantMapper {

    @Mapping(target = "question", ignore = true)
    Variant toEntity(VariantDto variantDto);

    VariantDto toDto(Variant variant);

    Set<VariantDto> toDtoSet(Set<Variant> variantSet);

    Set<Variant> toEntitySet(Set<VariantDto> variantDtoSet);
}
