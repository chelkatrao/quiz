package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.quiz.QuestionDto;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.service.UserSession;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private UserSession userSession;

    public QuestionMapper(UserSession userSession) {
        this.userSession = userSession;
    }

    public Question toQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setValue(questionDto.getValue());
        question.setId(questionDto.getId());
        question.setCreateBy(userSession.getUser().getUsername());
        return question;
    }

    public QuestionDto toQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .value(question.getValue())
                .build();
    }
}
