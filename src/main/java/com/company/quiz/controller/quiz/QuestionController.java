package com.company.quiz.controller.quiz;

import com.company.quiz.dto.quiz.QuestionDto;
import com.company.quiz.service.quiz.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz/question")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/new")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }

    @GetMapping("/list")
    public List<QuestionDto> listQuestion() {
        return questionService.listQuestion();
    }
}
