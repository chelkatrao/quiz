package com.company.quiz.dto.auth;

import com.company.quiz.enums.quiz.AnnualActivitiesEnum;
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
    private String nationalInnovation;
    private String numberOfWorkersEnum;
    private String positionEnum;
    private String sexEnum;
    private String typeOfActivityEnum;

    @Builder(builderMethodName = "builder")
    public UserCreateDto(Long id,
                         String username,
                         String password,
                         Set<Long> roleIds,
                         String annualActivitiesEnum,
                         String holdersOfAcademicDegreeEnum,
                         String informationEnum,
                         String nationalInnovation,
                         String numberOfWorkersEnum,
                         String positionEnum,
                         String sexEnum,
                         String typeOfActivityEnum,
                         Boolean isEnum,
                         String fullName,
                         String phone,
                         String email,
                         String pathOfCompany,
                         Double innovationPartPer
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
        this.annualActivitiesEnum = annualActivitiesEnum;
        this.holdersOfAcademicDegreeEnum = holdersOfAcademicDegreeEnum;
        this.informationEnum = informationEnum;
        this.nationalInnovation = nationalInnovation;
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
    }
}
