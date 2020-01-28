package com.company.quiz.mapper.auth;

import com.company.quiz.dto.auth.PermissionDto;
import com.company.quiz.dto.auth.RoleDto;
import com.company.quiz.model.auth.Permission;
import com.company.quiz.model.auth.Role;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {
    public Set<PermissionDto> listPermissionToListPermissionDto(Collection<Permission> list) {
        Set<PermissionDto> listPermissionDto = list.stream()
                .map(x -> PermissionDto.builder()
                        .id(x.getId())
                        .permissionInfo(x.getPermissionInfo())
                        .permissionName(x.getPermissionName())
                        .build()
                ).collect(Collectors.toSet());
        return listPermissionDto;
    }
}
