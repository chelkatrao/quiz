package com.company.quiz.dto.auth;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserCreateDto {

    private Long id;
    private String username;
    private String password;
    private Set<Long> roleIds;

    @Builder(builderMethodName = "builder")
    public UserCreateDto(Long id,
                         String username,
                         String password,
                         Set<Long> roleIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

}
