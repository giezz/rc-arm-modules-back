package ru.rightcode.anketi.model;

public enum QuestionTypeEnum {
    SINGLE_CHOICE ("SINGLE_CHOICE"),
    MULTIPLE_CHOICE("MULTI_CHOICE"),
    SCALE ("SCALE"),
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
