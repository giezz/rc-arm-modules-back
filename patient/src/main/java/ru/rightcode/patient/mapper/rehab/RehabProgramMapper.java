package ru.rightcode.patient.mapper.rehab;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.rehab.RehabProgramResponse;
import ru.rightcode.patient.model.RehabProgram;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RehabProgram.class,
                ModuleShortMapper.class,
                FormNoQuestionsResponseMapper.class,
                ProgramFormResponseMapper.class,
        })
public interface RehabProgramMapper {

//    @Mapping(target = "patient", source = "patient")
//    @Mapping(target = "doctor", source = "doctor")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "isCurrent", source = "isCurrent")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "forms", source = "forms")
    @Mapping(target = "modules", source = "modules")
    RehabProgramResponse toRehabProgramResponse(RehabProgram rehabProgram);
}
