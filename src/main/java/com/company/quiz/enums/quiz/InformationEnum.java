package com.company.quiz.enums.quiz;

public enum InformationEnum {

    OPTION_1("Фан номзоди (доктори)"),
    OPTION_2("Олий маълумот"),
    OPTION_3("Ўрта махсус маълумот"),
    OPTION_4("Ўрта маълумот"),
    OPTION_5("бошқа");

    private String value;

    InformationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
