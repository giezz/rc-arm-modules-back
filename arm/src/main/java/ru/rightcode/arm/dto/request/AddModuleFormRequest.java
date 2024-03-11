package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record AddModuleFormRequest(
        Long formId
) implements Serializable {
}
