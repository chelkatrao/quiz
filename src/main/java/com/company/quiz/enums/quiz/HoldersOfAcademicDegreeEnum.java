package com.company.quiz.enums.quiz;

public enum HoldersOfAcademicDegreeEnum {

    OPTION_1("1 нафар"),
    OPTION_2("2 дан 5 нафаргача"),
    OPTION_3("6 дан 25 нафаргача"),
    OPTION_4("26 дан ортиқ");

    private String value;

    HoldersOfAcademicDegreeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
