package com.company.quiz.enums.quiz;

public enum SexEnum {

    OPTION_1("эркак"),
    OPTION_2("аёл"),
    OPTION_3("30 ёшгача"),
    OPTION_4("30-60 ёшгача"),
    OPTION_5("60 ёшдан юқори");

    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
