package com.company.quiz.service.auth;

import com.company.quiz.enums.UserPermissionEnum;
import com.company.quiz.model.auth.Permission;
import com.company.quiz.model.auth.Role;
import com.company.quiz.repository.auth.PermissionRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private PermissionRepository permissionRepository;

    public AuthorityService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    //TODO: permissionlar bazada bor bo'lsa yozmaydigan qilish kerak
    public void basedAuthorizations() {
        UserPermissionEnum[] permissionEnums = UserPermissionEnum.values();
        for (UserPermissionEnum permisison : permissionEnums) {
            Permission newPermission = new Permission();
            newPermission.setPermissionName(permisison.name());
            newPermission.setPermissionInfo(permisison.name().toLowerCase()
                    .replace("_", " ").toLowerCase()
                    .replace(":", " ").toLowerCase() + " info"
                    .toLowerCase());
            newPermission.setCreateBy("Admin");
            permissionRepository.save(newPermission);
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
