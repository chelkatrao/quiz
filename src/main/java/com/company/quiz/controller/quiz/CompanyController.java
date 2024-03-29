package com.company.quiz.controller.quiz;

import com.company.quiz.dto.quiz.CompanyDto;
import com.company.quiz.mapper.quiz.CompanyMapper;
import com.company.quiz.service.quiz.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz/company")
public class CompanyController {

    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        return companyMapper.toCompanyDto(companyService.createCompany(companyDto));
    }

    @PostMapping("/list")
    public List<CompanyDto> listCompany() {
        return companyService.listCompany();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        return companyService.deleteCompanyById(id);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyDto companyDto) {
        return companyService.editCompany(companyDto);
    }

}
