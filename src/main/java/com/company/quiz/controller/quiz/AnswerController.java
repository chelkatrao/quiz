package com.company.quiz.controller.quiz;

import com.company.quiz.dto.quiz.AnswerDto;
import com.company.quiz.dto.quiz.SubAnswerDto;
import com.company.quiz.service.quiz.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("quiz/answer")
public class AnswerController {

    private AnswerService answerService;

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public String createAnswer(@RequestBody List<AnswerDto> answerDtoList) {
        return answerService.createAnswer(answerDtoList);
    }

    @GetMapping("/list/{questionId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<AnswerDto> listAnswerByQuestionId(@PathVariable("questionId") Long questionId) {
        return answerService.listAnswerByQuestionId(questionId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") Long id) {
        return answerService.deleteAnswer(id);
    }

    @GetMapping("/sub/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public ResponseEntity<?> deleteSubAnswer(@PathVariable("id") Long id) {
        return answerService.deleteSubAnswer(id);
    }

}
