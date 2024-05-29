package com.project.usersystem.mappers;

import com.project.usersystem.dto.userRole.RequestUserRoleDTO;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;

import java.util.Date;
import java.util.UUID;

public class UserRoleMapper {

    public static UserRoleEntity convertRequestUserRoleDTOtoUserEntity(RequestUserRoleDTO requestUserRoleDTO){
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleId(UUID.randomUUID().toString());
        userRoleEntity.setTsInsert(new Date());
        userRoleEntity.setTsUpdate(new Date());
        return userRoleEntity;
    }
}
