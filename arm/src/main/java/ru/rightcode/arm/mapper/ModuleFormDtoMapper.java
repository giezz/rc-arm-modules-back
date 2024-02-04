package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.ModuleFormDto;
import ru.rightcode.arm.model.ModuleForm;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModuleFormDtoMapper implements Mapper<ModuleForm, ModuleFormDto> {
    @Override
    public ModuleFormDto map(ModuleForm object) {
        return new ModuleFormDto(
                object.getId(),
                object.getForm().getId(),
                object.getForm().getName(),
                object.getBlock().getId(),
                object.getFinishedAt()
        );
    }

    public List<ModuleFormDto> mapAll(Collection<ModuleForm> moduleForms) {
        return moduleForms.stream().map(this::map).collect(Collectors.toList());
    }
}
