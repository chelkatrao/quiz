package com.company.quiz.enums.quiz;

public enum SexEnum {

    OPTION_1("эркак"),
    OPTION_2("аёл");


    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
