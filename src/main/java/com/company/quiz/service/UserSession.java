package com.company.quiz.service;

import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserSession {

    private UserRepository userRepository;

    public UserSession(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null) {
            user = userRepository.findByUsername((String) authentication.getPrincipal());
        }
        return user;
    }

    public String getUserRole() {
        User user = getUser();
        String userRoleName = null;
        if (user != null) {
            Collection<Role> roles = user.getRoles();
            if (!roles.isEmpty()) {
                userRoleName = roles.stream().findFirst().get().getRoleName();
            }
        }
        return userRoleName;
    }

}
