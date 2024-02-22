package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.VariantRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VariantDtoMapper implements Mapper<VariantDto, Variant> {
    private final VariantRepository variantRepository;
    @Override
    public Variant toEntity(VariantDto variantDto) {
        if (variantDto.getId() != null){
            Optional<Variant> variant = variantRepository.findById(variantDto.getId());
            if (variant.isPresent()){
                return variant.get();
            }else{
                throw new NotFoundException("Variant not found with id: "+variantDto.getId());
            }
        }
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
