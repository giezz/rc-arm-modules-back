package ru.rightcode.arm.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link ru.rightcode.arm.model.ModuleForm}
 */
public record ModuleFormRequest(Long formId, Long blockId) implements Serializable {
}