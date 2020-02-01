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
public class AnswerDto {

    private Long id;
    private String value;
    private Boolean isSub;
    private Long questionId;
    private List<SubAnswerDto> subAnswerDtoList;

    @Builder(builderMethodName = "builder")
    public AnswerDto(Long id,
                     String value,
                     Boolean isSub,
                     Long questionId,
                     List<SubAnswerDto> subAnswerDtoList) {
        this.id = id;
        this.value = value;
        this.isSub = isSub;
        this.questionId = questionId;
        this.subAnswerDtoList = subAnswerDtoList;
    }
}
