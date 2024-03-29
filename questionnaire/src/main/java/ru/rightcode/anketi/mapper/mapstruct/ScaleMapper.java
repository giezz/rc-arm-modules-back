package ru.rightcode.anketi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.anketi.dto.ScaleDto;
import ru.rightcode.anketi.model.Scale;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {QuestionMapper.class,
                VariantMapper.class,
                FormQuestionMapper.class})
public interface ScaleMapper {

    ScaleDto toDto(Scale scale);

    @Mapping(target = "interpretations", ignore = true)
    @Mapping(target = "forms", ignore = true)
    @Mapping(target = "id", source = "id")
    Scale toEntity(ScaleDto scaleDto);
}
