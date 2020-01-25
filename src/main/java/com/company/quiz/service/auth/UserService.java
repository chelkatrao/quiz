package com.company.quiz.service.auth;

import com.company.quiz.dto.auth.*;
import com.company.quiz.mapper.auth.UserMapper;
import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"userServiceCache"})
public class UserService {

    private UserRepository userRepository;

    private AuthorityService authorityService;

    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       AuthorityService authorityService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.userMapper = userMapper;
    }

    @Cacheable(key = "#root.methodName + '/' + #username")
    public UserDetailDto getUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        Set<SimpleGrantedAuthority> authentications = new HashSet<>();

        for (Role role : user.getRoles()) {
            authentications.addAll(authorityService.getGrantedAuthority(role));
        }

        UserDetailDto userDetailDto = new UserDetailDto(
                user.getUsername(),
                user.getPassword(),
                authentications
        );
        return userDetailDto;
    }

    @Cacheable(key = "#root.methodName")
    public List<UserDto> getUserList() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = users.stream()
                .map(user -> UserDto.builder()
                        .username(user.getUsername())
                        .roles(user.getRoles().stream()
                                .map(role -> RoleDto.builder()
                                        .roleInfo(role.getRoleInfo())
                                        .roleName(role.getRoleName())
                                        .permission(
                                                role.getPermissions().stream()
                                                        .map(permission ->
                                                                PermissionDto.builder()
                                                                        .permissionInfo(permission.getPermissionInfo())
                                                                        .permissionName(permission.getPermissionName())
                                                                        .builder()).collect(Collectors.toSet())
                                        ).builder()).collect(Collectors.toSet())
                        ).build()).collect(Collectors.toList());
        return userDtoList;
    }

    @CacheEvict
    @Transactional
    public UserCreateDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        user = userRepository.save(user);
        return userMapper.toCreateDto(user);
    }

}
