package com.company.quiz.dto;

import com.company.quiz.dto.quiz.QuizDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseQuziDate {

    private List<QuizDto> data;
    private Long total;
    private int page;

}
