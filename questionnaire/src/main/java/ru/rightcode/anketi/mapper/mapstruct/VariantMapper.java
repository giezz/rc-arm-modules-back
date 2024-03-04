package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.model.Variant;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VariantMapper {

    @Mapping(target = "question_id", ignore = true)
    @Mapping(target = "answers", ignore = true)
    Variant toEntity(VariantDto variantDto);

    VariantDto toDto(Variant variant);

    List<VariantDto> toDtoList(List<Variant> variantList);

    List<Variant> toEntityList(List<VariantDto> variantDtoList);
}
