package com.company.quiz.dto.auth;

import com.company.quiz.model.auth.Role;
import com.company.quiz.service.auth.AuthorityService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailDto {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> authentications;

    public UserDetailDto(String username,
                         String password,
                         Set<SimpleGrantedAuthority> authentications) {
        this.username = username;
        this.password = password;
        this.authentications = authentications;
    }

}
