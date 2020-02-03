package com.company.quiz.controller.quiz;

import com.company.quiz.dto.Pagination;
import com.company.quiz.dto.ResponseQuziDate;
import com.company.quiz.service.quiz.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("quiz/test")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseQuziDate getListQuiz(@RequestBody Pagination pagination) {
        return quizService.getListQuiz(pagination);
    }
}
