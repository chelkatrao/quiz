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
public class RoleCreateDto {

    private Long id;
    private String roleInfo;
    private String roleName;
    private Set<Long> permissionIds;

    public RoleCreateDto(Long id,
                         String roleInfo,
                         String roleName,
                         Set<Long> permissionIds) {
        this.id = id;
        this.roleInfo = roleInfo;
        this.roleName = roleName;
        this.permissionIds = permissionIds;
    }

}
