package com.company.quiz.controller.auth;

import com.company.quiz.dto.auth.UserCreateDto;
import com.company.quiz.dto.auth.UserDto;
import com.company.quiz.service.auth.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public List<UserDto> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN_READ')")
    public UserCreateDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

}
