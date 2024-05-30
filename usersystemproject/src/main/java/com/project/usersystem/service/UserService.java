package com.project.usersystem.service;


import com.project.usersystem.dto.LoginDTO;
import com.project.usersystem.dto.commons.SessionDTO;
import com.project.usersystem.dto.user.RequestUserDTO;
import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.exception.*;
import com.project.usersystem.mappers.UserMapper;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.repository.UserRoleRepository;
import com.project.usersystem.repository.UserStatusRepository;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;
import com.project.usersystem.repository.entities.UserStatusEntity;
import com.project.usersystem.utils.EncryptoUtils;
import com.project.usersystem.utils.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.project.usersystem.utils.UserStatusEnum.USER_DISABLED;
import static com.project.usersystem.utils.UserStatusEnum.USER_ENABLED;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserStatusRepository userStatusRepository;
    private EncryptoUtils encryptoUtils;
    private SessionService sessionService;
    private UserRoleRepository userRoleRepository;


    public UserDTO saveUser(RequestUserDTO requestUserDTO) throws BadRequestException {
        UserDTO userDTO = Optional.ofNullable(requestUserDTO)
                .map(userRequest -> userRequest.getRequest())
                .map(userSaveRequestDTO -> userSaveRequestDTO.getUser())
                .orElseThrow(() -> new BadRequestException("User cannot be null"));

        if (userRepository.existUser(userDTO.getUsername())) {
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


    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("The user not exist"));
        if (userEntity.getStatus().getStatusId() == USER_DISABLED.getStatus()) {
            throw new PreConditionFailException("The user already deleted");
        }
        UserStatusEntity userStatusEntity = getUserStatusEntity(USER_DISABLED);
        userEntity.setStatus(userStatusEntity);
        userEntity.setTsUpdate(new Date());
        userRepository.save(userEntity);
    }

    private UserStatusEntity getUserStatusEntity(UserStatusEnum userStatusEnum) {
        return userStatusRepository.findByUserStatusId(userStatusEnum.getStatus())
                .orElseThrow(() -> new InternalServerException("UserStatus doesn´t exist"));
    }


    public List<UserDTO> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> UserMapper.convertUserEntityToUserDTO(userEntity))
                .collect(Collectors.toList());
    }


    public SessionDTO login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UnauthenticatedException("The user credentials are incorrect"));
        if (!encryptoUtils.verifyPassword(loginDTO.getPassword(), userEntity.getPasswordHashed())) {
            throw new UnauthenticatedException("The user credentials are incorrect");
        }

        return sessionService.createSession(userEntity);
    }


    public void checkIfUserHasAllowedRoles(String idUser, String[] allowedRoles) {
        List<UserRoleEntity> userRoles = userRoleRepository.findByUser(idUser);
        if (userRoles.size() == 0) {
            throw new ForbiddenException("User has not permissions");
        }

        for (UserRoleEntity userRole : userRoles) {
            if (Arrays.asList(allowedRoles).contains(userRole.getRoleEntity().getRoleName())) {
                return;
            }
        }
        throw new ForbiddenException("User has not permissions");
    }


}
