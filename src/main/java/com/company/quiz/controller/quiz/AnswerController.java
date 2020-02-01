package com.company.quiz.controller.quiz;

import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.service.quiz.AnswerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz/answer")
public class AnswerController {

    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public List<AnswerDto> createAnswer(@RequestBody List<AnswerDto> answerDtoList) {
        return answerService.createAnswer(answerDtoList);
    }

    @GetMapping("/list/{permissionId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<AnswerDto> listAnswerByQuestionId(@PathVariable("permissionId") Long permissionId) {
        return answerService.listAnswerByQuestionId(permissionId);
    }

}
