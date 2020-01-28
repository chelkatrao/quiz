package com.company.quiz.controller.auth;

import com.company.quiz.dto.auth.PermissionDto;
import com.company.quiz.service.auth.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("auth/permission")
public class PermissionController {

    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<PermissionDto> getListPermission() {
        return permissionService.getAllPerms();
    }

    @GetMapping("/get/{permissionId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE','SUPER_ADMIN_READ')")
    public Set<PermissionDto> getPermissionByRoleId(@PathVariable("permissionId") Long userId) {
        return permissionService.getPermissionByRoleId(userId);
    }

}
