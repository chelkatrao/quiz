package com.company.quiz.repository.quiz;

import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.SubAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubAnswerRepository extends JpaRepository<SubAnswer,Long> {
    List<SubAnswer> findByAnswerId(Long answerId);
}
