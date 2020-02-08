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

    @Column(name = "value",length = 1000)
    private String value;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "is_sub")
    private Boolean isSub;

    @Column(name = "input_value")
    private String inputValue;

    @Column(name = "is_input")
    private Boolean isInput;

}
