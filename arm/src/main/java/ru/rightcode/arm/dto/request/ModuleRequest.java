package ru.rightcode.arm.dto.request;

import lombok.Value;

@Value
public class ModuleRequest {
    Long rehabProgramId;
    String moduleName;
}
