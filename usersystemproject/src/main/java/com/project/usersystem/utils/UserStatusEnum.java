package com.project.usersystem.utils;

public enum UserStatusEnum {

    USER_ENABLED (1),
    USER_DISABLED(2);

    private final int status;

    UserStatusEnum(Integer status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
