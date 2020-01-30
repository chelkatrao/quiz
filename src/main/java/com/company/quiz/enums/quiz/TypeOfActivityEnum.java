package com.company.quiz.enums.quiz;

public enum TypeOfActivityEnum {

    OPTION_1("Саноат"),
    OPTION_2("Қишлоқ хўжалиги"),
    OPTION_3("Транспорт ва алоқа"),
    OPTION_4("Савдо"),
    OPTION_5("Бошқа (кўрсатинг)"),
    OPTION_6("Бошқарув");

    private String value;

    TypeOfActivityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
