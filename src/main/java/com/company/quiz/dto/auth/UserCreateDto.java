package com.company.quiz.dto.auth;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    private Long id;
    private String username;
    private String password;
    private Set<Long> roleIds;
    private Boolean isEnum;

    private String fullName;
    private String phone;
    private String email;
    private String pathOfCompany;
    private Double innovationPartPer;

    //enums
    private String annualActivitiesEnum;
    private String holdersOfAcademicDegreeEnum;
    private String informationEnum;
    private String nationalInnovationEnum;
    private String numberOfWorkersEnum;
    private String positionEnum;
    private String sexEnum;
    private String typeOfActivityEnum;
    private Long companyId;
    private String companyName;
    private String companyCode;
    private String age;

    public UserCreateDto(Long id,
                         String username,
                         String password,
                         Set<Long> roleIds,
                         String annualActivitiesEnum,
                         String holdersOfAcademicDegreeEnum,
                         String informationEnum,
                         String nationalInnovationEnum,
                         String numberOfWorkersEnum,
                         String positionEnum,
                         String sexEnum,
                         String typeOfActivityEnum,
                         Boolean isEnum,
                         String fullName,
                         String phone,
                         String email,
                         String pathOfCompany,
                         Double innovationPartPer,
                         Long companyId,
                         String companyName,
                         String companyCode,
                         String age
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
        this.annualActivitiesEnum = annualActivitiesEnum;
        this.holdersOfAcademicDegreeEnum = holdersOfAcademicDegreeEnum;
        this.informationEnum = informationEnum;
        this.nationalInnovationEnum = nationalInnovationEnum;
        this.numberOfWorkersEnum = numberOfWorkersEnum;
        this.positionEnum = positionEnum;
        this.sexEnum = sexEnum;
        this.typeOfActivityEnum = typeOfActivityEnum;
        this.isEnum = isEnum;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.pathOfCompany = pathOfCompany;
        this.innovationPartPer = innovationPartPer;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.age = age;
    }
}
