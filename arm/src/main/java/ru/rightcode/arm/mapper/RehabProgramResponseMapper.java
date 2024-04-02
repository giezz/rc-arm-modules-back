package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.ModuleDto;
import ru.rightcode.arm.dto.response.PatientResponse;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RehabProgramResponseMapper implements Mapper<RehabProgram, RehabProgramResponse> {

    private final ModuleDtoMapper moduleDtoMapper;
    private final ProgramFormResponseMapper programFormResponseMapper;
    private final PatientResponseMapper patientResponseMapper;

    @Override
    public RehabProgramResponse map(RehabProgram object) {
        PatientResponse patientResponse = patientResponseMapper.map(object.getPatient());

        return new RehabProgramResponse(
                object.getId(),
                patientResponse,
                object.getIsCurrent(),
                object.getStartDate(),
                object.getEndDate(),
                null,
                null
        );
    }
    public RehabProgramResponse mapDetails(RehabProgram object) {
        final List<ModuleDto> moduleDtos = Optional.ofNullable(object.getModules())
                .map(modules -> modules.stream().map(moduleDtoMapper::map).toList())
                .orElse(null);
        final List<ProgramFormResponse> formsResponses = Optional.ofNullable(object.getForms())
                .map(forms -> forms.stream().map(programFormResponseMapper::map).toList())
                .orElse(null);

        return new RehabProgramResponse(
                object.getId(),
                null,
                object.getIsCurrent(),
                object.getStartDate(),
                object.getEndDate(),
                formsResponses,
                moduleDtos
        );
    }
}
