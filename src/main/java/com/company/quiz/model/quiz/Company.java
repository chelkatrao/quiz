package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name ="companyName",length = 50, unique = true, nullable = false)
    private String companyName;

    @NotNull
    @Column(name ="code",length = 5, unique = true, nullable = false)
    private String code;

}
