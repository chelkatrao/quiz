package com.company.quiz.controller.auth;

import com.company.quiz.dto.auth.RoleCreateDto;
import com.company.quiz.dto.auth.RoleDto;
import com.company.quiz.service.auth.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("auth/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ','SUPER_ADMIN_WRITE')")
    public Set<RoleDto> getListRoles() {
        return roleService.getListRoles();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ','SUPER_ADMIN_WRITE')")
    public RoleCreateDto createRole(@RequestBody RoleCreateDto roleCreateDto) {
        return roleService.createRole(roleCreateDto);
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public Boolean removeRole(@PathVariable("id") Long id) throws Exception {
        return roleService.removeRoleById(id);
    }

    @GetMapping("/get/{userId}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public Set<RoleDto> getRoleByUserId(@PathVariable("userId") Long userId) {
        return roleService.getRoleByUserId(userId);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public RoleCreateDto updateUser(@RequestBody RoleCreateDto roleCreateDto,
                                    @PathVariable("id") Long id) throws Throwable {
        return roleService.updateRole(roleCreateDto, id);
    }

}
