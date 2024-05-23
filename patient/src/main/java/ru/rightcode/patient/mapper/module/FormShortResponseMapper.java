package ru.rightcode.patient.mapper.module;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.dto.response.moduleShort.FormShortResponse;
import ru.rightcode.patient.model.Form;
import ru.rightcode.patient.model.ModuleForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Form.class, FormShortResponse.class, ModuleForm.class
        })
public interface FormShortResponseMapper {
    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "id", source = "form.id")
    @Mapping(target = "description", source = "form.description")
    FormShortResponse toFormShortResponse(ModuleForm moduleForm);
}
