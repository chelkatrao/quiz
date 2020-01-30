package com.company.quiz.enums.quiz;

public enum PositionEnum {

    OPTION_1("Корхона раҳбари"),
    OPTION_2("Раҳбар ўринбосари"),
    OPTION_3("Бўлим бошлиғи"),
    OPTION_4("Ишчи"),
    OPTION_5("Бошқа");

    private String value;

    PositionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
