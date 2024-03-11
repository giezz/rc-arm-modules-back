package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleDto;
import ru.rightcode.arm.dto.ModuleExerciseDto;
import ru.rightcode.arm.model.Module;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModuleDtoMapper implements Mapper<Module, ModuleDto> {

    @Override
    public ModuleDto map(Module object) {
        return new ModuleDto(
                object.getId(),
                object.getName(),
                object.getFinishedAt()
        );
    }

}
