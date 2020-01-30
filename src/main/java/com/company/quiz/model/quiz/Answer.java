package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question questionId;

    @Column(name = "is_sub")
    private Boolean isSub;

}
