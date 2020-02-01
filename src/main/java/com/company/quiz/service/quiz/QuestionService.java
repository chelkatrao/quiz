package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.QuestionDto;
import com.company.quiz.mapper.quiz.QuestionMapper;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.repository.quiz.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository,
                           QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = questionRepository.save(questionMapper.toQuestion(questionDto));
        return questionMapper.toQuestionDto(question);
    }

    public List<QuestionDto> listQuestion() {
        List<Question> questionList = questionRepository.findAll();
        return questionList.stream()
                .map(question -> questionMapper.toQuestionDto(question))
                .collect(Collectors.toList());
    }
}
