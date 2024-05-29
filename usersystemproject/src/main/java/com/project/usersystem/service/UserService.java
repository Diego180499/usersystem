package com.project.usersystem.service;


import com.project.usersystem.dto.user.RequestUserDTO;
import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.exception.InternalServerException;
import com.project.usersystem.exception.NotFoundException;
import com.project.usersystem.exception.PreConditionFailException;
import com.project.usersystem.mappers.UserMapper;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.repository.UserStatusRepository;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserStatusEntity;
import com.project.usersystem.utils.EncryptoUtils;
import com.project.usersystem.utils.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static com.project.usersystem.utils.UserStatusEnum.USER_DISABLED;
import static com.project.usersystem.utils.UserStatusEnum.USER_ENABLED;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserStatusRepository userStatusRepository;
    private EncryptoUtils encryptoUtils;

    public UserDTO saveUser(RequestUserDTO requestUserDTO) throws BadRequestException {
        UserDTO userDTO = Optional.ofNullable(requestUserDTO)
                        .map(userRequest -> userRequest.getRequest())
                        .map(userSaveRequestDTO -> userSaveRequestDTO.getUser())
                        .orElseThrow(() -> new BadRequestException("User cannot be null"));

        if(userRepository.existUser(userDTO.getUsername())){
            throw new PreConditionFailException("the user already exist");
        }
        UserEntity user = UserMapper.convertUserDTOtoUserEntity(userDTO);
        UserStatusEntity userStatusEntity = getUserStatusEntity(USER_ENABLED);
        user.setStatus(userStatusEntity);

        String passwordHashed = encryptoUtils.encryptPassword(userDTO.getPassword());
        user.setPasswordHashed(passwordHashed);

        user = userRepository.save(user);
        return UserMapper.convertUserEntityToUserDTO(user);
    }


    public void deleteUser(String userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("The user not exist"));
        if(userEntity.getStatus().getStatusId() == USER_DISABLED.getStatus()){
            throw new PreConditionFailException("The user already deleted");
        }
        UserStatusEntity userStatusEntity = getUserStatusEntity(USER_DISABLED);
        userEntity.setStatus(userStatusEntity);
        userRepository.save(userEntity);
    }

    private UserStatusEntity getUserStatusEntity(UserStatusEnum userStatusEnum){
        return userStatusRepository.findByUserStatusId(userStatusEnum.getStatus())
                .orElseThrow(()-> new InternalServerException("UserStatus doesn´t exist"));
    }


    public ArrayList<UserDTO> findAll(){
        ArrayList<UserEntity> userEntities = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity user: userEntities) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getUserId());
            userDTO.setName(user.getName());
            userDTO.setUsername(user.getUsername());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }


}
