package com.project.usersystem.repository;


import com.project.usersystem.repository.cruds.UserCrud;
import com.project.usersystem.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private UserCrud userCrud;

    public UserEntity save(UserEntity userEntity) {
        return userCrud.save(userEntity);
    }

    public boolean existUser(String userName) {
        try {
            UserEntity userEntity = userCrud.findByUsername(userName);
            Optional.ofNullable(userEntity).orElseThrow(() -> new NoSuchElementException());
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }


    public Optional<UserEntity> findById(String userId) {
        return userCrud.findById(userId);
    }

    public ArrayList<UserEntity> findAll() {
        return (ArrayList<UserEntity>) userCrud.findAll();
    }

    public Optional<UserEntity> findByUsername(String username) {
        try {
            return Optional.ofNullable(userCrud.findByUsername(username));
        } catch (NoSuchElementException nse) {
            return Optional.empty();
        }
    }


}
