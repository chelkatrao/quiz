package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.QuestionDto;
import com.company.quiz.mapper.quiz.QuestionMapper;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.model.quiz.SubAnswer;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.repository.quiz.QuestionRepository;
import com.company.quiz.repository.quiz.SubAnswerRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"questionServiceCache"})
public class QuestionService {

    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;
    private AnswerRepository answerRepository;
    private SubAnswerRepository subAnswerRepository;

    public QuestionService(QuestionRepository questionRepository,
                           QuestionMapper questionMapper,
                           AnswerRepository answerRepository,
                           SubAnswerRepository subAnswerRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.answerRepository = answerRepository;
        this.subAnswerRepository = subAnswerRepository;
    }

    @CacheEvict
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = questionRepository.save(questionMapper.toQuestion(questionDto));
        return questionMapper.toQuestionDto(question);
    }

    @Cacheable(key = "#root.method")
    public List<QuestionDto> listQuestion() {
        List<Question> questionList = questionRepository.findAll();
        return questionList.stream()
                .map(question -> questionMapper.toQuestionDto(question))
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict
    public ResponseEntity<?> deleteQuestion(Long id) {
        List<Answer> listAnswers = answerRepository.findByQuestionId(id);
        listAnswers.forEach(answer -> {
            List<SubAnswer> subAnswers = subAnswerRepository.findByAnswerId(answer.getId());
            if (!subAnswers.isEmpty()) {
                subAnswers.forEach(subAnswer -> {
                    subAnswerRepository.deleteById(subAnswer.getId());
                });
            }
            answerRepository.deleteById(answer.getId());
        });
        questionRepository.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
