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
    private Long id;
    private String username;
    private Set<RoleDto> roles;

    public UserDto(Long id,
                   String username,
                   Set<RoleDto> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
