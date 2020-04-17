package com.company.quiz.repository.auth;

import com.company.quiz.model.quiz.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyName(String system);

    Company findByCode(String companyCode);

    @Modifying // o'zgartirish
    @Override
    @Query("update Company c set c.isDeleted = 1 where c.id = :companyId")
    void deleteById(@Param("companyId") Long id);

    @Modifying // o'zgartirish
    @Override
    @Query("select c from Company c where c.isDeleted = 0")
    List<Company> findAll();

}
