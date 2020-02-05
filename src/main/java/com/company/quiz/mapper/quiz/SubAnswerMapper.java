package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.quiz.SubAnswerDto;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.SubAnswer;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.service.UserSession;
import org.springframework.stereotype.Component;

@Component
public class SubAnswerMapper {

    private UserSession userSession;
    private AnswerRepository answerRepository;

    public SubAnswerMapper(UserSession userSession,
                           AnswerRepository answerRepository) {
        this.userSession = userSession;
        this.answerRepository = answerRepository;
    }

    public SubAnswer toSubAnswer(SubAnswerDto subAnswerDto) {
        SubAnswer subAnswer = new SubAnswer();
        Answer parentAnswer = answerRepository.findById(subAnswerDto.getParentAnswer()).get();

        subAnswer.setId(subAnswerDto.getId());
        subAnswer.setAnswer(parentAnswer);
        subAnswer.setIsInput(subAnswerDto.getIsInput());
        subAnswer.setCreateBy(userSession.getUser().getUsername());
        subAnswer.setInputValue(subAnswerDto.getInputValue());
        subAnswer.setValue(subAnswerDto.getValue());
        return subAnswer;
    }

    public SubAnswerDto toSubAnswerDto(SubAnswer subAnswer) {
        return SubAnswerDto.builder()
                .id(subAnswer.getId())
                .value(subAnswer.getValue())
                .isInput(subAnswer.getIsInput())
                .inputValue(subAnswer.getInputValue())
                .parentAnswer(subAnswer.getAnswer().getId())
                .build();
    }

}
