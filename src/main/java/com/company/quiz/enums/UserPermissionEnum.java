package com.company.quiz.enums;

public enum UserPermissionEnum {

    SUPER_ADMIN_READ("super_admin:read"),
    SUPER_ADMIN_WRITE("super_admin:write"),

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private String permission;

    UserPermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
