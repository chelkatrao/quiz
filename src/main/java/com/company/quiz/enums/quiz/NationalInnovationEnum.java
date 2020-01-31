package com.company.quiz.enums.quiz;

public enum NationalInnovationEnum {

    OPTION_1("Ҳа. Маҳсулотларни сотиш бозорларини кенгайтириш"),
    OPTION_2("Ҳа. Маҳсулотларни сотишни янги йўналишларини очиш имконияти"),
    OPTION_3("Бошқа варианлар");

    private String value;

    NationalInnovationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
