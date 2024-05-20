package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.ModuleFormResponse;
import ru.rightcode.arm.model.ModuleForm;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModuleFormsResponseMapper implements Mapper<ModuleForm, ModuleFormResponse> {

    private final FormResponseMapper formResponseMapper;

    @Override
    public ModuleFormResponse map(ModuleForm object) {
        final FormResponse form = Optional.ofNullable(object.getForm())
                .map(formResponseMapper::map)
                .orElse(null);

        return new ModuleFormResponse(
                object.getId(),
                object.getModule().getId(),
                form,
                object.getFinishedAt()
        );
    }
}
