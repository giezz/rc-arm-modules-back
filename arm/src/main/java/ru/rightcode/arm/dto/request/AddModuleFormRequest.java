package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record AddModuleFormRequest(
        Long formId,
        Long blockId
) implements Serializable {
}
