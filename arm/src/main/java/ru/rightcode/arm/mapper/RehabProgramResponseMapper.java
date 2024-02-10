package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.ModuleDto;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RehabProgramResponseMapper implements Mapper<RehabProgram, RehabProgramResponse> {

    private final ModuleDtoMapper moduleDtoMapper;

    private final FormResponseMapper formResponseMapper;

    @Override
    public RehabProgramResponse map(RehabProgram object) {
        final List<ModuleDto> moduleDtos = Optional.ofNullable(object.getModules())
                .map(moduleDtoMapper::mapAll)
                .orElse(null);
        final FormResponse startForm = Optional.ofNullable(object.getStartForm())
                .map(formResponseMapper::map)
                .orElse(null);
        final FormResponse endForm = Optional.ofNullable(object.getEndForm())
                .map(formResponseMapper::map)
                .orElse(null);

        return new RehabProgramResponse(
                object.getId(),
                object.getIsCurrent(),
                object.getStartDate(),
                object.getEndDate(),
                startForm,
                endForm,
                moduleDtos
        );
    }
}
