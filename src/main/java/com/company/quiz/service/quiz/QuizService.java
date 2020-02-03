package com.company.quiz.service.quiz;

import com.company.quiz.dto.Pagination;
import com.company.quiz.dto.ResponseQuziDate;
import com.company.quiz.mapper.quiz.QuizMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

@Service
@CacheConfig(cacheNames = {"quizServiceCache"})
public class QuizService {

    private QuizMapper quizMapper;
    private EntityManagerFactory entityManagerFactory;

    public QuizService(QuizMapper quizMapper,
                       EntityManagerFactory entityManagerFactory) {
        this.quizMapper = quizMapper;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Cacheable(key = "#root.methodName + '/' + #pagination")
    public ResponseQuziDate getListQuiz(Pagination pagination) {
        return quizMapper.getQuizDto(pagination);
    }
}
