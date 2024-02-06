package ru.rightcode.back.dto.request;

import lombok.Value;

@Value
public class ModuleRequest {
    Long rehabProgramId;
    String moduleName;
}
