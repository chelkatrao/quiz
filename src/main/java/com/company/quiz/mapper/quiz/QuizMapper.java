package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.Pagination;
import com.company.quiz.dto.ResponseQuziDate;
import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.dto.quiz.QuizDto;
import com.company.quiz.dto.quiz.SubAnswerDto;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.repository.quiz.QuestionRepository;
import com.company.quiz.repository.quiz.SubAnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private SubAnswerRepository subAnswerRepository;

    public QuizMapper(QuestionRepository questionRepository, AnswerRepository answerRepository, SubAnswerRepository subAnswerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.subAnswerRepository = subAnswerRepository;
    }

    public ResponseQuziDate getQuizDto(Pagination pagination) {

        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getLimit());
        Page<Question> all = questionRepository.findAll(pageable);

        List<Question> data = all.getContent();
        Long total = all.getTotalElements();
        int page = all.getTotalPages();

        List<QuizDto> quizDtos = data
                .stream()
                .map(question ->
                        QuizDto.builder()
                                .questionId(question.getId())
                                .questionValue(question.getValue())
                                .answerDtos(answerRepository.findByQuestionId(question.getId())
                                        .stream()
                                        .map(answer ->
                                                AnswerDto.builder()
                                                        .id(answer.getId())
                                                        .value(answer.getValue())
                                                        .questionId(answer.getQuestion().getId())
                                                        .subAnswerDtoList(
                                                                subAnswerRepository.findByAnswerId(answer.getId())
                                                                        .stream()
                                                                        .map(subAnswer ->
                                                                                SubAnswerDto.builder()
                                                                                        .id(subAnswer.getId())
                                                                                        .parentAnswer(answer.getId())
                                                                                        .value(subAnswer.getValue())
                                                                                        .build()
                                                                        ).collect(Collectors.toList())
                                                        ).build()
                                        ).collect(Collectors.toList())
                                ).build()
                ).collect(Collectors.toList());

        return ResponseQuziDate.builder()
                .data(quizDtos)
                .page(page)
                .total(total)
                .build();

    }

}
