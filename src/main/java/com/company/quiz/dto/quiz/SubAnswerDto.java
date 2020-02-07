package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubAnswerDto {

    private Long id;
    private String value;
    private Boolean isInput;
    private String inputValue;
    private Long parentAnswer;

    public SubAnswerDto(Long id,
                        String value,
                        Boolean isInput,
                        String inputValue,
                        Long parentAnswer) {
        this.id = id;
        this.isInput = isInput;
        this.value = value;
        this.inputValue = inputValue;
        this.parentAnswer = parentAnswer;
    }

}
