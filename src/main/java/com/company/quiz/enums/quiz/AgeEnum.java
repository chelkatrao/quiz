package com.company.quiz.enums.quiz;

public enum AgeEnum {
    OPTION_1("30 ёшгача"),
    OPTION_2("30-60 ёшгача"),
    OPTION_3("60 ёшдан юқори");

    private String value;

    AgeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
