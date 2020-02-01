package com.company.quiz.service.auth;

import com.company.quiz.enums.UserPermissionEnum;
import com.company.quiz.model.auth.Permission;
import com.company.quiz.model.auth.Role;
import com.company.quiz.model.auth.User;
import com.company.quiz.repository.auth.PermissionRepository;
import com.company.quiz.repository.auth.RoleRepository;
import com.company.quiz.repository.auth.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;


    public AuthorityService(PermissionRepository permissionRepository,
                            RoleRepository roleRepository,
                            UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //TODO: permissionlar bazada bor bo'lsa yozmaydigan qilish kerak
    public void createPermission() {
        Permission permission = permissionRepository.findByPermissionName(UserPermissionEnum.SUPER_ADMIN_READ.name());
        if (permission==null) {
            UserPermissionEnum[] permissionEnums = UserPermissionEnum.values();
            for (UserPermissionEnum permisison : permissionEnums) {
                Permission newPermission = new Permission();
                newPermission.setPermissionName(permisison.name());
                newPermission.setPermissionInfo(permisison.name().toLowerCase()
                        .replace("_", " ").toLowerCase()
                        .replace(":", " ").toLowerCase() + " info"
                        .toLowerCase());
                newPermission.setCreateBy("system");
                permissionRepository.save(newPermission);
            }
        }
    }

    @Transactional
    public void createRole() {
        Role isRoleExist = roleRepository.findByRoleName("SUPER_ADMIN_ROLE");
        if (isRoleExist == null) {
            Role role = new Role();
            role.setRoleName("SUPER_ADMIN_ROLE");
            role.setRoleInfo("super admin role");
            role.setPermissions(Sets.newHashSet(permissionRepository.findAll()));
            role.setCreateBy("system");
            roleRepository.save(role);
        }
    }

    @Transactional
    public void createUser() {
        User isUserExist = userRepository.findByUsername("admin");
        if (isUserExist == null) {
            User user = new User();
            Role isRoleExist = roleRepository.findByRoleName("SUPER_ADMIN_ROLE");
            user.setPassword("$2y$10$at14q5DfRC7YZ1bBovgFRu.O8YWyDVsemePzUpTe6dEuUybyfATtq");
            user.setUsername("admin");
            user.setRoles(Sets.newHashSet(isRoleExist));
            user.setPhone("998999999999");
            user.setFullName("superadmin");
            user.setCreateBy("system");
            userRepository.save(user);
        }
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(Role role) {
        Set<SimpleGrantedAuthority> permission = role.getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermissionName()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority(role.getRoleName()));
        return permission;
    }
}
