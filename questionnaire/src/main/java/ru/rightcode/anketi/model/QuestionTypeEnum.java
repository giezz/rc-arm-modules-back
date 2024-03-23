package ru.rightcode.anketi.model;

public enum QuestionTypeEnum {
    SINGLE_CHOICE ("single_choice"),
    MULTIPLE_CHOICE("multiple_choice"),
    SCALE ("scale"),
    ;

    private final String title;
    QuestionTypeEnum(String s) {
        this.title = s;
    }

    public String getTitle() {
        return title;
    }
    @Override
    public String toString() {
        return title;
    }
}
