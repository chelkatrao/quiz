package com.company.quiz.service.auth;

import com.company.quiz.dto.auth.RoleCreateDto;
import com.company.quiz.dto.auth.RoleDto;
import com.company.quiz.mapper.auth.RoleMapper;
import com.company.quiz.model.auth.Role;
import com.company.quiz.repository.auth.RoleRepository;
import com.company.quiz.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = {"roleServiceCache"})
public class RoleService {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private RoleMapper roleMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RoleService(RoleRepository roleRepository,
                       UserRepository userRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleMapper = roleMapper;
    }

    @CacheEvict
    @Transactional
    public RoleCreateDto createRole(RoleCreateDto roleCreateDto) {
        Role role = roleMapper.toRole(roleCreateDto);
        role.setRoleName(role.getRoleName().toUpperCase());
        if (!role.getRoleName().endsWith("_ROLE"))
            role.setRoleName(role.getRoleName() + "_ROLE");
        role = roleRepository.save(role);
        return roleMapper.toCreateDto(role);
    }

    @Cacheable(key = "#root.methodName")
    public Set<RoleDto> getListRoles() {
        List<Role> listRole = roleRepository.findAll();
        return roleMapper.listRoleToListRoleDto(listRole);
    }

    @CacheEvict
    public Boolean removeRoleById(Long id) throws Exception {
        try {
            jdbcTemplate.update(" delete from role_permission r where r.role_id =? ", id);
            jdbcTemplate.update(" delete from roles r where r.id = ? ", id);
            return true;
        } catch (Exception e) {
            throw new Exception("User not deleted!!!");
        }
    }

    @Cacheable(key = "#root.methodName")
    public Set<RoleDto> getRoleByUserId(Long userId) {
        Set<Role> listRole = userRepository.findById(userId).get().getRoles();
        return roleMapper.listRoleToListRoleDto(listRole);
    }

    @Cacheable(key = "#root.methodName")
    public RoleCreateDto updateRole(RoleCreateDto roleCreateDto, Long id) throws Exception {
        try {
            Role role = roleMapper.toRole(roleCreateDto, roleRepository.findById(id).get());
            role.setId(id);
            Role editedRole = roleRepository.save(role);
            return roleMapper.toCreateDto(editedRole);
        } catch (Exception e) {
            throw new Exception("User not updated something want wrong!!!");
        }
    }

    @Cacheable(key = "#root.methodName")
    public RoleCreateDto getRoleByName(String roleName) {
        return roleMapper.toCreateDto(roleRepository.findByRoleName(roleName));
    }

}
