package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

    @Column(name = "value", length = 1000)
    private String value;

    @Column(name = "is_deleted",nullable = false)
    @JsonIgnore
    private Integer isDeleted = 0;

}
