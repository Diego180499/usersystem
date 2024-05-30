package com.project.usersystem.service;

import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.dto.userRole.RequestAddUserRoleDTO;
import com.project.usersystem.dto.userRole.RequestUserRoleDTO;
import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.exception.NotFoundException;
import com.project.usersystem.exception.PreConditionFailException;
import com.project.usersystem.mappers.UserRoleMapper;
import com.project.usersystem.repository.RoleRepository;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.repository.UserRoleRepository;
import com.project.usersystem.repository.entities.RoleEntity;
import com.project.usersystem.repository.entities.UserEntity;
import com.project.usersystem.repository.entities.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleService {
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public void addUserRole(RequestUserRoleDTO requestUserRoleDTO) {
        Optional<RequestAddUserRoleDTO> maybeRequestAddUserRoleDTO = Optional.ofNullable(requestUserRoleDTO)
                .map(requestUserRoleDTO1 -> requestUserRoleDTO1.getRequest());
        UserDTO userDTO = maybeRequestAddUserRoleDTO
                .map(request -> request.getUser())
                .orElseThrow(() -> new BadRequestException("The user cannot be null"));
        RoleDTO roleDTO = maybeRequestAddUserRoleDTO
                .map(requestAdd -> requestAdd.getRole())
                .orElseThrow(() -> new BadRequestException("Role cannot be null"));

        UserEntity userEntity = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        RoleEntity roleEntity = roleRepository.findById(roleDTO.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        Optional<UserRoleEntity> maybeUserRoleEntity = userRoleRepository.findByUserAndRole(userEntity, roleEntity);
        if (maybeUserRoleEntity.isPresent()) {
            throw new PreConditionFailException("The role has been added to the user previously");
        }

        UserRoleEntity userRoleEntity = UserRoleMapper.convertRequestUserRoleDTOtoUserEntity(requestUserRoleDTO);
        userRoleEntity.setUserEntity(userEntity);
        userRoleEntity.setRoleEntity(roleEntity);
        userRoleRepository.save(userRoleEntity);
    }


}
