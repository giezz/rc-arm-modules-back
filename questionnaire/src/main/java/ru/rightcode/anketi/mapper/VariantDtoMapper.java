package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.model.Variant;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VariantDtoMapper implements Mapper<VariantDto, Variant> {
    @Override
    public Variant toEntity(VariantDto variantDto) {
        Variant variant = new Variant();
        variant.setId(variantDto.getId());
        variant.setContent(variantDto.getContent());
        variant.setScore(variantDto.getScore());
        return variant;
    }

    @Override
    public VariantDto toDto(Variant variant){
        return VariantDto.builder()
                .id(variant.getId())
                .content(variant.getContent())
                .score(variant.getScore())
                .build();
    }

    public List<VariantDto> variantDtoList(List<Variant> variantList){
        return variantList.stream()
                .map(this::toDto)
                .toList();
    }
}
