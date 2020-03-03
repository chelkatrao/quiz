package com.company.quiz.repository.quiz;

import com.company.quiz.model.quiz.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a from Answer a where a.isDeleted = 0 and a.question.id = :questionId")
    List<Answer> findByQuestionId(@Param("questionId") Long question);

    @Modifying // o'zgartirish
    @Override
    @Query("select a from Answer a where a.isDeleted = 0")
    List<Answer> findAll();

    @Modifying // o'zgartirish
    @Override
    @Query("update Answer a set a.isDeleted = 1 where a.id = :answerId")
    void deleteById(@Param("answerId") Long id);
}
