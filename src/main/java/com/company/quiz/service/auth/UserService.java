package com.company.quiz.service.auth;

import com.company.quiz.dto.auth.*;
import com.company.quiz.mapper.auth.UserMapper;
import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.UserRepository;
import com.company.quiz.service.UserSession;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Types;
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

    private UserSession userSession;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(UserRepository userRepository,
                       AuthorityService authorityService,
                       UserMapper userMapper,
                       UserSession userSession) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.userMapper = userMapper;
        this.userSession = userSession;
    }

    @CacheEvict
    @Transactional
    public UserCreateDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        user = userRepository.save(user);
        return userMapper.toCreateDto(user);
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
    public List<UserDto> getUserListFull() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .roles(user.getRoles().stream()
                                .map(role -> RoleDto.builder()
                                        .id(role.getId())
                                        .roleInfo(role.getRoleInfo())
                                        .roleName(role.getRoleName())
                                        .permission(
                                                role.getPermissions().stream()
                                                        .map(permission ->
                                                                PermissionDto.builder()
                                                                        .permissionInfo(permission.getPermissionInfo())
                                                                        .permissionName(permission.getPermissionName())
                                                                        .build()).collect(Collectors.toSet())
                                        ).build()).collect(Collectors.toSet())
                        ).build()).collect(Collectors.toList());
        return userDtoList;
    }

    @Cacheable(key = "#root.methodName")
    public List<UserDto> getUserList() {
        List<User> list = userRepository.findAll();
        return userMapper.listUserToListUserDto(list);
    }

    @CacheEvict
    @Transactional
    public Boolean removeUserById(Long id) throws Exception {
        try {
            jdbcTemplate.update(" delete from user_role r where r.user_id =? ", id);
            jdbcTemplate.update(" delete from users u where u.id = ? ", id);
            return true;
        } catch (Exception e) {
            throw new Exception("User not deleted!!!");
        }
    }

    @CacheEvict
    public UserCreateDto updateUser(UserCreateDto userUpdateDto, Long id) throws Exception {
        try {
            User user = userMapper.toUser(userUpdateDto, userRepository.findById(id).get());
            user.setId(id);
            user.setPassword(null);
            User editedUser = userRepository.save(user);
            return userMapper.toCreateDto(editedUser);
        } catch (Exception e) {
            throw new Exception("User not updated something want wrong!!!");
        }
    }
}
