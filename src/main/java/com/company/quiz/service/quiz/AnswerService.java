package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.mapper.quiz.AnswerMapper;
import com.company.quiz.mapper.quiz.SubAnswerMapper;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.SubAnswer;
import com.company.quiz.repository.quiz.AnswerRepository;
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
@CacheConfig(cacheNames = {"answerServiceCache"})
public class AnswerService {

    private AnswerRepository answerRepository;
    private SubAnswerRepository subAnswerRepository;
    private SubAnswerMapper subAnswerMapper;
    private AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository,
                         SubAnswerMapper subAnswerMapper,
                         AnswerMapper answerMapper,
                         SubAnswerRepository subAnswerRepository) {
        this.answerRepository = answerRepository;
        this.subAnswerMapper = subAnswerMapper;
        this.answerMapper = answerMapper;
        this.subAnswerRepository = subAnswerRepository;
    }

    @Transactional
    @CacheEvict
    public String createAnswer(List<AnswerDto> answerDtoList) {
        answerDtoList
                .forEach(answerDto -> {
                    Answer answer = answerMapper.toAnswer(answerDto);
                    answer = answerRepository.save(answer);
                    if (answerDto.getSubAnswerDtoList() != null) {
                        Answer finalAnswer = answer;
                        answerDto.getSubAnswerDtoList().forEach(subAnswerDto -> {
                                    subAnswerDto.setParentAnswer(finalAnswer.getId());
                                    SubAnswer subAnswer = subAnswerMapper.toSubAnswer(subAnswerDto);
                                    subAnswerRepository.save(subAnswer);
                                }
                        );
                    }
                });
        return "success";
    }

    @Cacheable(key = "#root.method")
    public List<AnswerDto> listAnswerByQuestionId(Long questionId) {
        List<Answer> listAnswers = answerRepository.findByQuestionId(questionId);
        return listAnswers.stream()
                .map(answer -> AnswerDto.builder()
                        .id(answer.getId())
                        .isSub(answer.getIsSub())
                        .value(answer.getValue())
                        .questionId(answer.getQuestion().getId())
                        .build()
                ).collect(Collectors.toList());
    }

    @CacheEvict
    @Transactional
    public ResponseEntity<?> deleteAnswer(Long id) {
        List<SubAnswer> subAnswerList = subAnswerRepository.findByAnswerId(id);
        subAnswerList.forEach(subAnswer -> {
            subAnswerRepository.delete(subAnswer);
        });
        answerRepository.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @CacheEvict
    public ResponseEntity<?> deleteSubAnswer(Long id) {
        subAnswerRepository.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
