package com.company.quiz.repository.auth;

import com.company.quiz.model.quiz.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyName(String system);

    Company findByCode(String companyCode);
}
