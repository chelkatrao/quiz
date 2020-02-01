package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.mapper.quiz.AnswerMapper;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.repository.quiz.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository,
                         AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    public List<AnswerDto> createAnswer(List<AnswerDto> answerDtoList) {
        List<Answer> listAnswer = answerDtoList
                .stream().map(answerDto -> answerMapper.toAnswer(answerDto))
                .collect(Collectors.toList());
        List<Answer> answers = answerRepository.saveAll(listAnswer);
        return answers.stream()
                .map(answer -> answerMapper.toAnswerDto(answer))
                .collect(Collectors.toList());
    }

    public List<AnswerDto> listAnswerByQuestionId(Long permissionId) {
        List<Answer> listAnswers = answerRepository.findByQuestionId(permissionId);
        return listAnswers.stream()
                .map(answer -> AnswerDto.builder()
                        .id(answer.getId())
                        .isSub(answer.getIsSub())
                        .value(answer.getValue())
                        .questionId(answer.getQuestion().getId())
                        .build()
                ).collect(Collectors.toList());
    }
}
