package com.company.quiz.mapper.auth;

import com.company.quiz.dto.auth.UserCreateDto;
import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.RoleRepository;
import com.company.quiz.service.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private UserSession userSession;

    public UserMapper(PasswordEncoder passwordEncoder,
                      RoleRepository roleRepository,
                      UserSession userSession) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userSession = userSession;
    }

    public User toUser(UserCreateDto userCreateDto) {
        return toUser(userCreateDto);
    }

    public User toUser(UserCreateDto userCreateDto, User user) {
        if (userCreateDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setUsername(userCreateDto.getUsername());
        user.setCreateBy(userSession.getUser().getUsername());
        if (userCreateDto.getRoleIds() != null) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : userCreateDto.getRoleIds()) {
                Role role = roleRepository.findById(roleId).get();
                roles.add(role);
            }
            user.setRoles(roles);
        } else {
            user.setRoles(null);
        }
        return user;
    }

    public UserCreateDto toCreateDto(User user) {
        UserCreateDto dto = new UserCreateDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        Set<Long> roleIds = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleIds.add(role.getId());
        }

        dto.setRoleIds(roleIds);
        return dto;
    }

}
