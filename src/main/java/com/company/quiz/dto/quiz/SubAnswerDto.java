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
    private String values;
    private AnswerDto answer;

    @Builder(builderMethodName = "builder")
    public SubAnswerDto(Long id,
                        String values,
                        AnswerDto answer) {
        this.id = id;
        this.values = values;
        this.answer = answer;
    }
}
