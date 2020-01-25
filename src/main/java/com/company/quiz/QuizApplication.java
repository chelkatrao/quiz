package com.company.quiz;

import com.company.quiz.service.auth.AuthorityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(QuizApplication.class, args);
//      applicationContext.getBean(AuthorityService.class).basedAuthorizations();
//      TODO:comment basedAuthorizations method t'o'g'ri ishlagandan keyin olib tashlanishi kerak
    }

}
