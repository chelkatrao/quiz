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
    private Boolean isSub;
    private Long questionId;

    @Builder(builderMethodName = "builder")
    public AnswerDto(Long id,
                     String value,
                     Boolean isSub,
                     Long questionId
    ) {
        this.id = id;
        this.value = value;
        this.isSub = isSub;
        this.questionId = questionId;
    }
}
