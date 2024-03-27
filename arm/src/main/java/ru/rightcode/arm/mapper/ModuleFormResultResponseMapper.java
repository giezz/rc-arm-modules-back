package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.ModuleFormResultResponse;
import ru.rightcode.arm.model.ModuleForm;

@Component
@RequiredArgsConstructor
public class ModuleFormResultResponseMapper implements Mapper<ModuleForm, ModuleFormResultResponse> {

    private final FormResponseMapper formResponseMapper;

    @Override
    public ModuleFormResultResponse map(ModuleForm object) {
        FormResponse formResponse = formResponseMapper.map(object.getForm());
        String interpretation = null;
        if (object.getForm().getScale() != null) {
            interpretation = object.getForm().getScale().getInterpretations().get(0).getDescription();
        }

        return new ModuleFormResultResponse(
                object.getId(),
                formResponse,
                object.getModule().getName(),
                object.getFinishedAt(),
                object.getScore(),
                interpretation
        );
    }
}
