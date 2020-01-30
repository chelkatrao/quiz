package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
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

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

}
