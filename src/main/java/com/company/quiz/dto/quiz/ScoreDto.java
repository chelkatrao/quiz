package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ScoreDto {

    private Long id;
    private Long userId;
    private Long questionId;
    private Long answerId;
    private Long subAnswerId;

    @Builder(builderMethodName = "builder")
    public ScoreDto(Long id,
                    Long userId,
                    Long questionId,
                    Long answerId,
                    Long subAnswerId) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.subAnswerId = subAnswerId;
    }
}
