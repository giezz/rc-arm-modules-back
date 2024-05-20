package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.*;
import ru.rightcode.arm.dto.ModuleDto;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RehabProgramResponseMapper implements Mapper<RehabProgram, RehabProgramResponse> {

    private final ModuleDtoMapper moduleDtoMapper;
    private final ProgramFormResponseMapper programFormResponseMapper;
    private final PatientResponseMapper patientResponseMapper;
    private final DoctorResponseMapper doctorResponseMapper;

    @Override
    public RehabProgramResponse map(RehabProgram object) {
        PatientResponse patientResponse = patientResponseMapper.map(object.getPatient());
        DoctorResponse doctorResponse = doctorResponseMapper.map(object.getDoctor());

        return new RehabProgramResponse(
                object.getId(),
                patientResponse,
                doctorResponse,
                object.getIsCurrent(),
                object.getStartDate(),
                object.getEndDate(),
                null,
                null
        );
    }

    public RehabProgramResponse mapFull(RehabProgram object) {
        PatientResponse patientResponse = patientResponseMapper.map(object.getPatient());
        DoctorResponse doctorResponse = doctorResponseMapper.map(object.getDoctor());
        final List<ModuleDto> moduleDtos = Optional.ofNullable(object.getModules())
                .map(modules -> modules.stream().map(moduleDtoMapper::map).toList())
                .orElse(null);
        final List<ProgramFormResponse> formsResponses = Optional.ofNullable(object.getForms())
                .map(forms -> forms.stream().map(programFormResponseMapper::map).toList())
                .orElse(null);

        return new RehabProgramResponse(
                object.getId(),
                patientResponse,
                doctorResponse,
                object.getIsCurrent(),
                object.getStartDate(),
                object.getEndDate(),
                formsResponses,
                moduleDtos
        );
    }
}
