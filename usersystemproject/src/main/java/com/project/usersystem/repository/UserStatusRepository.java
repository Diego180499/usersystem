package com.project.usersystem.repository;


import com.project.usersystem.repository.cruds.UserStatusCrud;
import com.project.usersystem.repository.entities.UserStatusEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStatusRepository {

    private UserStatusCrud userStatusCrud;

    public Optional<UserStatusEntity> findByUserStatusId(Integer id){
        try{
            return Optional.ofNullable(userStatusCrud.findByStatusId(id));
        }catch (Exception e){
            return Optional.empty();
        }
    }


}
