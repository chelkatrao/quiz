package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.quiz.ScoreDto;
import com.company.quiz.model.auth.User;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.model.quiz.Score;
import com.company.quiz.model.quiz.SubAnswer;
import com.company.quiz.repository.auth.CompanyRepository;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.repository.quiz.QuestionRepository;
import com.company.quiz.repository.quiz.SubAnswerRepository;
import com.company.quiz.service.UserSession;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper {

    private UserSession userSession;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private SubAnswerRepository subAnswerRepository;

    public ScoreMapper(UserSession userSession,
                       QuestionRepository questionRepository,
                       AnswerRepository answerRepository,
                       SubAnswerRepository subAnswerRepository) {
        this.userSession = userSession;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.subAnswerRepository = subAnswerRepository;
    }

    public Score toScore(ScoreDto scoreDto, Long subAnswerId) {
        Score score = new Score();
        User currentUser = userSession.getUser();
        Question question = null;
        Answer answer = null;
        SubAnswer subAnswer = null;

        if (scoreDto.getQuestionId() != null)
            question = questionRepository.findById(scoreDto.getQuestionId()).get();

        if (scoreDto.getAnswerId() != null)
            answer = answerRepository.findById(scoreDto.getAnswerId()).get();

        if (scoreDto.getSubAnswerId() != null)
            subAnswer = subAnswerRepository.findById(subAnswerId).get();

        score.setUser(userSession.getUser());
        score.setQuestion(question);
        score.setAnswer(answer);
        score.setSubAnswer(subAnswer);
        score.setAnswerInputValue(scoreDto.getAnswerInputValue());
        score.setSubAnswerInputValue(scoreDto.getSubAnswerInputValue());
        score.setCreateBy(currentUser.getUsername());
        score.setCompany(currentUser.getCompany());
        return score;
    }
}
