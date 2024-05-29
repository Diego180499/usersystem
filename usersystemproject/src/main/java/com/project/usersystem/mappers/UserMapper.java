package com.project.usersystem.mappers;

import com.project.usersystem.dto.user.request.UserDTO;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserStatusEntity;

import java.util.Date;
import java.util.UUID;

public class UserMapper {

    public static UserEntity convertUserDTOtoUserEntity(UserDTO userDTO){
        UserEntity newUser = new UserEntity();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setName(userDTO.getName());
        newUser.setUsername(userDTO.getUsername());
        newUser.setTsInsert(new Date()); //12312345698
        newUser.setTsUpdate(new Date());
        return newUser;
    }

    public static UserDTO convertUserEntityToUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getUserId());
        userDTO.setName(userEntity.getName());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setStatus(userEntity.getStatus().getStatusName());
        userDTO.setTsInsert(userEntity.getTsInsert().getTime());
        return  userDTO;
    }






}
