package com.company.quiz.controller.auth;

import com.company.quiz.dto.auth.UserCreateDto;
import com.company.quiz.dto.auth.UserDto;
import com.company.quiz.service.auth.RoleService;
import com.company.quiz.service.auth.UserService;
import com.google.common.collect.Sets;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-full")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<UserDto> getUserListFull() {
        return userService.getUserListFull();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<UserDto> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("/new")
    public String createUser(@RequestBody UserCreateDto userCreateDto) throws Exception {
        userCreateDto.setRoleIds(Sets.newHashSet(roleService.getRoleByName("USER_ROLE").getId()));
        userService.createUser(userCreateDto);
        return "success";
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public Boolean removeUser(@PathVariable("id") Long id) throws Exception {
        return userService.removeUserById(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_WRITE')")
    public UserCreateDto updateUser(@RequestBody UserCreateDto userUpdateDto,
                                    @PathVariable("id") Long id) throws Throwable {
        return userService.updateUser(userUpdateDto, id);
    }
}
