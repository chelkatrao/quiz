package com.company.quiz.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PermissionDto {
    private Long id;
    private String permissionInfo;
    private String permissionName;

    @Builder(buildMethodName = "builder")
    public PermissionDto(Long id,
                         String permissionInfo,
                         String permissionName) {
        this.id = id;
        this.permissionInfo = permissionInfo;
        this.permissionName = permissionName;
    }

}
