package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnswerDto {

    private Long id;
    private String value;
    private Boolean isInput;
    private String inputValue;
    private Long questionId;
    private Boolean isSub;
    private List<SubAnswerDto> subAnswerDtoList;
    private Boolean isEnableInput;

    public AnswerDto(Long id,
                     String value,
                     Boolean isInput,
                     String inputValue,
                     Long questionId,
                     Boolean isSub,
                     List<SubAnswerDto> subAnswerDtoList,
                     Boolean isEnableInput
    ) {
        this.id = id;
        this.value = value;
        this.inputValue = inputValue;
        this.isSub = isSub;
        this.isInput = isInput;
        this.questionId = questionId;
        this.subAnswerDtoList = subAnswerDtoList;
        this.isEnableInput = isEnableInput;
    }

}
