package com.company.quiz.dto.auth;

import com.company.quiz.model.auth.User;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserCreateDto {

    private String username;
    private String password;
    private Set<Long> roleIds;

    @Builder(builderMethodName = "builder")
    public UserCreateDto(String username,
                         String password,
                         Set<Long> roleIds) {
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

}
