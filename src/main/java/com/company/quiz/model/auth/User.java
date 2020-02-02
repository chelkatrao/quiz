package com.company.quiz.model.auth;

import com.company.quiz.enums.quiz.*;
import com.company.quiz.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 50, nullable = false)
    private String password;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "phone", length = 12, nullable = false)
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "path_of_company")
    private String pathOfCompany;

    @Column(name = "innovation_part_per")
    private Double innovationPartPer;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Set<Role> roles;

    @Column(name = "annual_activities",length = 100)
    private String annualActivitiesEnum;

    @Column(name = "holders_of_academic_degree",length = 100)
    private String holdersOfAcademicDegreeEnum;

    @Column(name = "information_enum",length = 100)
    private String informationEnum;

    @Column(name = "national_information", length = 100)
    private String nationalInnovationEnum;

    @Column(name = "number_of_workers", length = 100)
    private String numberOfWorkersEnum;

    @Column(name = "position",length = 100)
    private String positionEnum;

    @Column(name = "sex",length = 100)
    private String sexEnum;

    @Column(name = "type_of_activity",length = 100)
    private String typeOfActivityEnum;

}
