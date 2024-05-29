package com.project.usersystem.service;


import com.project.usersystem.dto.role.RequestRoleDTO;
import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.exception.PreConditionFailException;
import com.project.usersystem.mappers.RoleMapper;
import com.project.usersystem.repository.RoleRepository;
import com.project.usersystem.repository.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;


    public RoleDTO save(RequestRoleDTO requestRoleDTO){
        RoleDTO roleDTO = Optional.ofNullable(requestRoleDTO)
                .map(requestroleDTO -> requestroleDTO.getRequest())
                .map(requestsaveDTO -> requestsaveDTO.getRole())
                .orElseThrow(()-> new BadRequestException("The Role cannot be null"));
        if(roleRepository.existRole(roleDTO.getRolename())){
            throw new PreConditionFailException("The Role already exist");
        }

        RoleEntity roleEntity = RoleMapper.convertRoleDTOtoRoleEntity(roleDTO);
        roleEntity = roleRepository.save(roleEntity);
        return RoleMapper.convertRoleEntityToRoleDTO(roleEntity);
    }


    public ArrayList<RoleDTO> findAll(){
        ArrayList<RoleEntity> roleEntities = roleRepository.findAll();
        ArrayList<RoleDTO> roleDTOS = new ArrayList<>();

        for (RoleEntity role : roleEntities) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(role.getRoleId());
            roleDTO.setRolename(role.getRoleName());
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

}
