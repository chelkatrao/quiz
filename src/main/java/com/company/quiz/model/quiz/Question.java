package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
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

    @Column(name = "value")
    private String value;

}
