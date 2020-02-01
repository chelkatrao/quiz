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
    private Long parentAnswer;

    @Builder(builderMethodName = "builder")
    public SubAnswerDto(Long id,
                        String value,
                        Long parentAnswer) {
        this.id = id;
        this.value = value;
        this.parentAnswer = parentAnswer;
    }
}
