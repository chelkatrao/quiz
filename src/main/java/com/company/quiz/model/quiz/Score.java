package com.company.quiz.model.quiz;

import com.company.quiz.model.BaseEntity;
import com.company.quiz.model.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "scores")
public class Score extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "users", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer", nullable = false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "sub_answer")
    private SubAnswer subAnswer;

    @Column(name="answer_input_value")
    private String answerInputValue;

    @Column(name="sub_answer_input_value")
    private String subAnswerInputValue;

}
