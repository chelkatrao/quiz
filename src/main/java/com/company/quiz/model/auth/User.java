package com.company.quiz.model.auth;

import com.company.quiz.enums.quiz.*;
import com.company.quiz.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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


    @Enumerated(EnumType.STRING)
    @Column(name = "annual_activities")
    private AnnualActivitiesEnum annualActivitiesEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "holders_of_academic_degree")
    private HoldersOfAcademicDegreeEnum holdersOfAcademicDegreeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "information_enum")
    private InformationEnum informationEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "national_information")
    private NationalInnovation nationalInnovation;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_of_workers")
    private NumberOfWorkersEnum numberOfWorkersEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private PositionEnum positionEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexEnum sexEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_activity")
    private TypeOfActivityEnum typeOfActivityEnum;

}
