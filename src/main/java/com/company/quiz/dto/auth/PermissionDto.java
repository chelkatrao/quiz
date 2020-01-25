package com.company.quiz.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PermissionDto {

    private String permissionInfo;
    private String permissionName;

    @Builder(buildMethodName = "builder")
    public PermissionDto(String permissionInfo,
                         String permissionName) {
        this.permissionInfo = permissionInfo;
        this.permissionName = permissionName;
    }

}
