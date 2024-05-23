package ru.rightcode.patient.dto.response.form;

import lombok.Getter;

@Getter
public enum QuestionTypeEnum {
    SINGLE_CHOICE ("SINGLE_CHOICE"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    SCALE ("SCALE"),
    ;

    private final String title;
    QuestionTypeEnum(String s) {
        this.title = s;
    }

    @Override
    public String toString() {
        return title;
    }
}
