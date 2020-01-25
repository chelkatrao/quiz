package com.company.quiz.service.auth;

import com.company.quiz.dto.auth.PermissionDto;
import com.company.quiz.model.auth.Permission;
import com.company.quiz.repository.auth.PermissionRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"userServiceCache"})
public class PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Cacheable(key = "#root.methodName + '/' + #username")
    public List<PermissionDto> getAllPerms() {
        List<Permission> listPermission = permissionRepository.findAll();
        List<PermissionDto> permissionDtoList = listPermission.stream()
                .map(x -> PermissionDto.builder()
                        .permissionName(x.getPermissionName())
                        .permissionInfo(x.getPermissionInfo()).builder())
                .collect(Collectors.toList());
        return permissionDtoList;
    }

}
