package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.CompanyDto;
import com.company.quiz.mapper.quiz.CompanyMapper;
import com.company.quiz.model.quiz.Company;
import com.company.quiz.repository.auth.CompanyRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"companyServiceCache"})
public class CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository,
                          CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @CacheEvict
    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyMapper.toCompanyDto(
                companyRepository.save(companyMapper.toCompany(companyDto))
        );
    }

    @Cacheable(key = "#root.method")
    public List<CompanyDto> listCompany() {
        return companyMapper.toCompanyDto(companyRepository.findAll());
    }

    @CacheEvict
    public ResponseEntity<?> deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
        return ResponseEntity.ok("success deleted");
    }

    @CacheEvict
    public ResponseEntity editCompany(CompanyDto companyDto) {
        Company company = companyRepository.findById(companyDto.getId()).get();
        company.setCompanyName(companyDto.getCompanyName());
        return new ResponseEntity<>(companyMapper.toCompanyDto(company), HttpStatus.OK);
    }

    @CacheEvict
    public Boolean findByCode(String companyCode) {
        return companyRepository.findByCode(companyCode) != null;
    }
}
