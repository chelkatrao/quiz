package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.repository.quiz.QuestionRepository;
import com.company.quiz.service.UserSession;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    private UserSession userSession;
    private QuestionRepository questionRepository;

    public AnswerMapper(UserSession userSession,
                        QuestionRepository questionRepository) {
        this.userSession = userSession;
        this.questionRepository = questionRepository;
    }

    public Answer toAnswer(AnswerDto answerDto) {
        Answer answer = new Answer();
        Question question = questionRepository.getOne(answerDto.getQuestionId());

        answer.setIsSub(answerDto.getIsSub());
        answer.setValue(answerDto.getValue());
        answer.setIsInput(answerDto.getIsInput());
        answer.setInputValue(answerDto.getInputValue());
        answer.setQuestion(question);
        answer.setCreateBy(userSession.getUser().getUsername());
        return answer;
    }

    public AnswerDto toAnswerDto(Answer answer) {
        AnswerDto answerDto = AnswerDto.builder()
                .id(answer.getId())
                .isSub(answer.getIsSub())
                .isInput(answer.getIsInput())
                .questionId(answer.getQuestion().getId())
                .value(answer.getValue())
                .inputValue(answer.getInputValue())
                .build();
        return answerDto;
    }

}
