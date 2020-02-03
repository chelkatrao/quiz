package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class QuizDto {

    private Long questionId;
    private String questionValue;
    private List<AnswerDto> answerDtos;

    public QuizDto(Long questionId,
                   String questionValue,
                   List<AnswerDto> answerDtos) {
        this.questionId = questionId;
        this.questionValue = questionValue;
        this.answerDtos = answerDtos;
    }

}
