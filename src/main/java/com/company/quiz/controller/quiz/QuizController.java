package com.company.quiz.controller.quiz;

import com.company.quiz.dto.Pagination;
import com.company.quiz.dto.ResponseQuziDate;
import com.company.quiz.service.quiz.QuizService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz/test")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public ResponseQuziDate getListQuiz(@RequestBody Pagination pagination) {
        return quizService.getListQuiz(pagination);
    }
}
