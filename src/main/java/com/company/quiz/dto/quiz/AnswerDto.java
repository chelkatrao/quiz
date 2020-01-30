package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    private String value;
    private Set<SubAnswerDto> subAnswer;
    private Boolean isSub;

    @Builder(builderMethodName = "builder")
    public AnswerDto(Long id,
                     String value,
                     Set<SubAnswerDto> subAnswer,
                     Boolean isSub) {
        this.id = id;
        this.value = value;
        this.subAnswer = subAnswer;
        this.isSub = isSub;
    }
}
