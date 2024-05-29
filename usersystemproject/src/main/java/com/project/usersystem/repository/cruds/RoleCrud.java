package com.project.usersystem.repository.cruds;


import com.project.usersystem.repository.entities.RoleEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCrud extends CrudRepository<RoleEntity,String> {


    @Query(value = "SELECT * FROM role WHERE role_name = ?", nativeQuery = true)
    RoleEntity findByRoleName(String roleName);
}
