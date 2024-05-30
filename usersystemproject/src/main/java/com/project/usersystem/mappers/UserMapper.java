package com.project.usersystem.mappers;

import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.repository.entities.UserEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity convertUserDTOtoUserEntity(UserDTO userDTO) {
        UserEntity newUser = new UserEntity();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setName(userDTO.getName());
        newUser.setUsername(userDTO.getUsername());
        newUser.setTsInsert(new Date()); //12312345698
        newUser.setTsUpdate(new Date());
        return newUser;
    }

    public static UserDTO convertUserEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getUserId());
        userDTO.setName(userEntity.getName());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setStatus(userEntity.getStatus().getStatusName());
        userDTO.setTsInsert(userEntity.getTsInsert().getTime() / 1000);
        userDTO.setTsUpdate(userEntity.getTsUpdate().getTime() / 1000);
        userDTO.setRoles(RoleMapper.convertRolesEntityToRolesDTO(
                userEntity.getUserRoleEntities() == null ? List.of() :
                        userEntity.getUserRoleEntities().stream()
                                .map(userrole -> userrole.getRoleEntity())
                                .collect(Collectors.toList())));
        return userDTO;
    }


}
