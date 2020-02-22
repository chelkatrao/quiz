package com.company.quiz.controller;

import com.company.quiz.service.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/{questionId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ','SUPER_ADMIN_WRITE')")
    public Map reportPercentage(@PathVariable Long questionId) {
        return reportService.reportPercentage(questionId);
    }

    @PostMapping("/{answerId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ','SUPER_ADMIN_WRITE')")
    public List<HashMap> companyByAnswer(@PathVariable Long answerId) {
        return reportService.companyByAnswer(answerId);
    }

}
