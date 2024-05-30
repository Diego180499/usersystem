package com.project.usersystem.repository;

import com.project.usersystem.repository.cruds.UserRoleCrud;
import com.project.usersystem.repository.entities.RoleEntity;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRoleRepository {

    private final UserRoleCrud userRoleCrud;

    public UserRoleEntity save(UserRoleEntity userRoleEntity) {
        return userRoleCrud.save(userRoleEntity);
    }


    public Optional<UserRoleEntity> findByUserAndRole(UserEntity userEntity, RoleEntity roleEntity) {
        try {
            return Optional.ofNullable(userRoleCrud.findByUserAndRole(userEntity.getUserId(), roleEntity.getRoleId()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<UserRoleEntity> findByUser(String userEntityId) {
        try {
            List<UserRoleEntity> userRoleEntities = userRoleCrud.findByUser(userEntityId);
            return Objects.requireNonNullElseGet(userRoleEntities, List::of);

        } catch (Exception e) {
            return List.of();
        }
    }

}
