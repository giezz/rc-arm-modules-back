package ru.rightcode.arm.dto.request;

import lombok.Getter;

public record AddFormRequest(
        Long formId,
        FormType formType
) {
    @Getter
    public enum FormType {
        START(1L),
        END(2L);

        private final long code;

        FormType(long code) {
            this.code = code;
        }
    }
}
