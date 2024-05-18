package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.ModuleFormResultResponse;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.dto.response.ProgramFormResultResponse;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.model.ProgramForm;

@Component
@RequiredArgsConstructor
public class ProgramFormResultResponseMapper implements Mapper<ProgramForm, ProgramFormResultResponse> {

    private final FormResponseMapper formResponseMapper;

    @Override
    public ProgramFormResultResponse map(ProgramForm object) {
        FormResponse formResponse = formResponseMapper.map(object.getForm());
        String interpretation = null;
        if (object.getForm().getScale() != null) {
            interpretation = object.getForm().getScale().getInterpretations().get(0).getDescription();
        }

        return new ProgramFormResultResponse(
                object.getId(),
                formResponse,
                object.getType().getId(),
                object.getFinishedAt(),
                object.getScore(),
                interpretation
        );
    }
}
