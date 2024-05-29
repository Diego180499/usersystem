package com.project.usersystem.repository;


import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.mappers.UserMapper;
import com.project.usersystem.repository.cruds.UserCrud;
import com.project.usersystem.repository.cruds.UserStatusCrud;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserStatusEntity;
import com.project.usersystem.utils.EncryptoUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private UserStatusCrud userStatusCrud;
    private UserCrud userCrud;

    public UserEntity save(UserEntity userEntity) {
        return userCrud.save(userEntity);
    }

    public boolean existUser(String userName){
        try{
            UserEntity userEntity =  userCrud.findByUsername(userName);
            Optional.ofNullable(userEntity).orElseThrow(()-> new NoSuchElementException());
            return true;
        }catch (NoSuchElementException nsee){
            return false;
        }
    }


    public Optional<UserEntity> findById(String userId){
        return userCrud.findById(userId);
    }


}
