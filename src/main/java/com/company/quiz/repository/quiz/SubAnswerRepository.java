package com.company.quiz.repository.quiz;

import com.company.quiz.model.quiz.SubAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAnswerRepository extends JpaRepository<SubAnswer,Long> {
}
