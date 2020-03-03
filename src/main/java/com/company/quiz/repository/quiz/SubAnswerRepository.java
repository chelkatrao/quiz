package com.company.quiz.repository.quiz;

import com.company.quiz.model.quiz.SubAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubAnswerRepository extends JpaRepository<SubAnswer,Long> {

    @Query("select s from SubAnswer s where s.isDeleted=0")
    List<SubAnswer> findByAnswerId(Long answerId);

    @Modifying // o'zgartirish
    @Override
    @Query("select s from SubAnswer s where s.isDeleted=0")
    List<SubAnswer> findAll();

    @Modifying // o'zgartirish
    @Override
    @Query("update SubAnswer s set s.isDeleted=1 where s.id=:SubAnswerId")
    void deleteById(@Param("SubAnswerId") Long id);

}
