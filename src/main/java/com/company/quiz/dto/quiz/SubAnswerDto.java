package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class SubAnswerDto {

    private Long id;
    private String value;
    private String inputValue;
    private Boolean isInput;
    private Long parentAnswer;

    public SubAnswerDto(Long id,
                        Boolean isInput,
                        String inputValue,
                        String value,
                        Long parentAnswer) {
        this.id = id;
        this.isInput = isInput;
        this.value = value;
        this.inputValue = inputValue;
        this.parentAnswer = parentAnswer;
    }

}
