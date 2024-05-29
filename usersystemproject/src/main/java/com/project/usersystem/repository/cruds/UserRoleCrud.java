package com.project.usersystem.repository.cruds;

import com.project.usersystem.repository.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleCrud extends CrudRepository<UserRoleEntity, String> {

    @Query(value = "SELECT * FROM user_role WHERE user_id = ? AND role_id = ?", nativeQuery = true)
    UserRoleEntity findByUserAndRole(String userId, String roleId);
}
