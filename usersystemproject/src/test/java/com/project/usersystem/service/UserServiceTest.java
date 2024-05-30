package com.project.usersystem.service;

import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.dto.user.RequestUserDTO;
import com.project.usersystem.dto.user.RequestUserSaveDTO;
import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.exception.PreConditionFailException;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.repository.UserRoleRepository;
import com.project.usersystem.repository.UserStatusRepository;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserStatusEntity;
import com.project.usersystem.utils.EncryptoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserStatusRepository userStatusRepository;
    @Mock
    private EncryptoUtils encryptoUtils;
    @Mock
    private SessionService sessionService;
    @Mock
    private UserRoleRepository userRoleRepository;


    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userStatusRepository, encryptoUtils, sessionService, userRoleRepository);

    }

    @Test
    void saveUserWhenInvalidRequestThenBadRequestException() {
        RequestUserDTO requestUserDTO = new RequestUserDTO();
        assertThrows(BadRequestException.class, () -> userService.saveUser(requestUserDTO));
        requestUserDTO.setRequest(new RequestUserSaveDTO());
        assertThrows(BadRequestException.class, () -> userService.saveUser(requestUserDTO));
    }


    @Test
    void saveUserWhenUserExistThenPreConditionFailException() {
        UserDTO userDTO = new UserDTO();
        RequestUserSaveDTO requestUserSaveDTO = new RequestUserSaveDTO();
        requestUserSaveDTO.setUser(userDTO);
        RequestUserDTO requestUserDTO = new RequestUserDTO();
        requestUserDTO.setRequest(requestUserSaveDTO);

        when(userRepository.existUser(any())).thenReturn(true);
        assertThrows(PreConditionFailException.class, () -> userService.saveUser(requestUserDTO));
    }


    @Test
    void saveUserWhenIsOk() {
        UserDTO userDTO = new UserDTO();
        RequestUserSaveDTO requestUserSaveDTO = new RequestUserSaveDTO();
        requestUserSaveDTO.setUser(userDTO);
        RequestUserDTO requestUserDTO = new RequestUserDTO();
        requestUserDTO.setRequest(requestUserSaveDTO);
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(new UserStatusEntity());
        userEntity.setTsUpdate(new Date());
        userEntity.setTsInsert(new Date());

        when(userRepository.existUser(any())).thenReturn(false);
        when(userStatusRepository.findByUserStatusId(any())).thenReturn(Optional.ofNullable(new UserStatusEntity()));
        when(encryptoUtils.encryptPassword(any())).thenReturn("hashedpasss");
        when(userRepository.save(any())).thenReturn(userEntity);

        userService.saveUser(requestUserDTO);
        verify(userRepository, times(1)).save(any());
    }


}