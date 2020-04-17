package com.company.quiz.enums.quiz;

public enum AnnualActivitiesEnum {

    OPTION_1("1 йил"),
    OPTION_2("1-3 йил"),
    OPTION_3("4-6 йил"),
    OPTION_4("7-9 йил"),
    OPTION_5("10 й. ва ундан юқори");

    private String value;

    AnnualActivitiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
