package com.company.quiz.dto.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ScoreDto {

    private Long id;
    @JsonIgnore
    private Long userId;
    private Long questionId;
    private Long answerId;
    private List<Long> subAnswerId;
    private String answerInputValue;
    private String subAnswerInputValue;
    private Long companyId;

    public ScoreDto(Long id,
                    Long userId,
                    Long questionId,
                    Long answerId,
                    List<Long> subAnswerId,
                    String answerInputValue,
                    String subAnswerInputValue,
                    Long companyId) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.subAnswerId = subAnswerId;
        this.answerInputValue = answerInputValue;
        this.subAnswerInputValue = subAnswerInputValue;
        this.companyId = companyId;
    }
}
