package com.company.quiz.enums.quiz;

public enum NationalInnovation {

    OPTION_1("Ҳа. Маҳсулотларни сотиш бозорларини кенгайтириш"),
    OPTION_2("Ҳа. Маҳсулотларни сотишни янги йўналишларини очиш имконияти"),
    OPTION_3("Бошқа варианлар");

    private String value;

    NationalInnovation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
