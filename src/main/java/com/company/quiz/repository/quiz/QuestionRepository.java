package com.company.quiz.repository.quiz;

import com.company.quiz.model.quiz.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying // o'zgartirish
    @Override
    @Query("select q from Question q where q.isDeleted = 0")
    List<Question> findAll();

    @Override
    @Query(value = "select q from Question q where q.isDeleted = 0 order by q.id",
            countQuery = "select count(q) from Question q where q.isDeleted = 0")
    Page<Question> findAll(Pageable pageable);

    @Modifying // o'zgartirish
    @Override
    @Query("update Question q set q.isDeleted = 1 where q.id = :questionId")
    void deleteById(@Param("questionId") Long id);

    //    @Modifying // o'zgartirish
    @Override
    @Query("select count(a) from Question a where a.isDeleted = 0")
    long count();

}
