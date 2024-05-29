package com.project.usersystem.repository.cruds;

import com.project.usersystem.repository.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrud extends CrudRepository<UserEntity, String> {

    UserEntity findByUsername(String username);


}
