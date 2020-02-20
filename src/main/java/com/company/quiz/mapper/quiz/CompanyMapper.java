package com.company.quiz.mapper.quiz;

import com.company.quiz.dto.quiz.CompanyDto;
import com.company.quiz.model.quiz.Company;
import com.company.quiz.service.UserSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    private UserSession userSession;

    public CompanyMapper(UserSession userSession) {
        this.userSession = userSession;
    }

    public Company toCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCreateBy(userSession.getUser().getUsername());
        return company;
    }

    public CompanyDto toCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .build();
    }

    public List<Company> toCompany(List<CompanyDto> companyDtoList) {
        return companyDtoList
                .stream()
                .map(companyDto -> toCompany(companyDto))
                .collect(Collectors.toList());
    }

    public List<CompanyDto> toCompanyDto(List<Company> companyList) {
        return companyList
                .stream()
                .map(company -> toCompanyDto(company))
                .collect(Collectors.toList());
    }

}
