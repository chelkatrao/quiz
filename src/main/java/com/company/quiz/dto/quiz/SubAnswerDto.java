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
    private String values;

    @Builder(builderMethodName = "builder")
    public SubAnswerDto(Long id,
                        String values
                        ) {
        this.id = id;
        this.values = values;
    }
}
