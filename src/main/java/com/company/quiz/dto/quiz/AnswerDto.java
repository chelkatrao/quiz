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
    private String inputValue;
    private Boolean isSub;
    private Boolean isInput;
    private Long questionId;
    private List<SubAnswerDto> subAnswerDtoList;

    public AnswerDto(Long id,
                     String value,
                     String inputValue,
                     Boolean isInput,
                     Boolean isSub,
                     Long questionId,
                     List<SubAnswerDto> subAnswerDtoList) {
        this.id = id;
        this.value = value;
        this.inputValue = inputValue;
        this.isSub = isSub;
        this.isInput = isInput;
        this.questionId = questionId;
        this.subAnswerDtoList = subAnswerDtoList;
    }

}
