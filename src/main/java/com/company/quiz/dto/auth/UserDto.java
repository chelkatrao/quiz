package com.company.quiz.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserDto {

    private String username;
    private Set<RoleDto> roles;

    @Builder(builderMethodName = "builder")
    public UserDto(String username,
                   Set<RoleDto> roles) {
        this.username = username;
        this.roles = roles;
    }

}
