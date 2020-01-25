package com.company.quiz.dto.auth;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class RoleDto {

    private String roleInfo;
    private String roleName;
    private Set<PermissionDto> permission;

    @Builder(buildMethodName = "builder")
    public RoleDto(String roleInfo,
                   String roleName,
                   Set<PermissionDto> permission) {
        this.roleInfo = roleInfo;
        this.roleName = roleName;
        this.permission = permission;
    }

}
