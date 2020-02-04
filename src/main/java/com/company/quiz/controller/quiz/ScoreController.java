package com.company.quiz.controller.quiz;

import com.company.quiz.dto.quiz.ScoreDto;
import com.company.quiz.service.quiz.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz/scoring")
public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public ResponseEntity<?> scoring(@RequestBody List<ScoreDto> listScoreDto) {
        return scoreService.scoring(listScoreDto);
    }

}
