package com.company.quiz.enums.quiz;

public enum NumberOfWorkersEnum {

    OPTION_1("1 дан 50 нафаргача"),
    OPTION_2("51 дан 200 нафаргача"),
    OPTION_3("201 дан 500 нафаргача");

    private String value;

    NumberOfWorkersEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
