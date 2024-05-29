package com.project.usersystem.repository;

import com.project.usersystem.repository.cruds.UserRoleCrud;
import com.project.usersystem.repository.entities.RoleEntity;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRoleRepository {

    private final UserRoleCrud userRoleCrud;

    public UserRoleEntity save(UserRoleEntity userRoleEntity){
            return userRoleCrud.save(userRoleEntity);
    }


    public Optional<UserRoleEntity> findByUserAndRole(UserEntity userEntity, RoleEntity roleEntity){
        try{
            return Optional.ofNullable(userRoleCrud.findByUserAndRole(userEntity.getUserId(), roleEntity.getRoleId()));
        }catch (Exception e){
            return Optional.empty();
        }
    }

}
