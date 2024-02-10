package ru.rightcode.arm.dto.request;

public record AddFormRequest(
        Long formId,
        FormType formType
) {
    public enum FormType {
        START,
        END
    }
}
