package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sub_answer")
public class SubAnswer extends BaseEntity {

    @Column(name = "value")
    private String value;

    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column(name = "input_value")
    private String inputValue;

    @Column(name = "is_input")
    private Boolean isInput;

    @Column(name = "is_deleted",nullable = false)
    @JsonIgnore
    private Integer isDeleted = 0;

}
