package com.company.quiz.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String companyName;
    private String code;

    public CompanyDto(Long id,
                      String companyName,
                      String code) {
        this.id = id;
        this.companyName = companyName;
        this.code = code;
    }
}
