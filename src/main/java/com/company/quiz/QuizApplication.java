package com.company.quiz;

import com.company.quiz.service.auth.AuthorityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        // TODO: Service larni cachelab chiqish kerak ! :(
        ConfigurableApplicationContext applicationContext = SpringApplication.run(QuizApplication.class, args);
        applicationContext.getBean(AuthorityService.class).createCompany();// create company
        applicationContext.getBean(AuthorityService.class).createPermission();// create permission
        applicationContext.getBean(AuthorityService.class).createRole();// create admin role
        applicationContext.getBean(AuthorityService.class).createUserRole();// create user role
        applicationContext.getBean(AuthorityService.class).createUser();// create user
    }

}
