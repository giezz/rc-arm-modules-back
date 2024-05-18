package ru.rightcode.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.rightcode.patient.model.Module;
import ru.rightcode.patient.model.ModuleForm;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {Module.class, ModuleForm.class
        })
public interface ModuleMapper {
}
