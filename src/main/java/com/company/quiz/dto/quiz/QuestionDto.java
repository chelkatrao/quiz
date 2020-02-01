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
public class QuestionDto {

    private Long id;
    private String value;

    @Builder(builderMethodName = "builder")
    public QuestionDto(Long id,
                       String value) {
        this.id = id;
        this.value = value;
    }
}
