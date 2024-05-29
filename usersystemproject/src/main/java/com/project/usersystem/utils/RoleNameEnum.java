package com.project.usersystem.utils;

public enum RoleNameEnum {

    ADMIN ("ADMIN"),
    EMPLOYEE ("EMPLOYEE");

    private  final String rolename;

    RoleNameEnum(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }
}
