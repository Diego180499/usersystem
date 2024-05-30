package com.project.usersystem.mappers;

import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.repository.entities.RoleEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleEntity convertRoleDTOtoRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(UUID.randomUUID().toString());
        roleEntity.setRoleName(roleDTO.getRolename());
        roleEntity.setTsInsert(new Date());
        roleEntity.setTsUpdate(new Date());
        return roleEntity;
    }

    public static RoleDTO convertRoleEntityToRoleDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleEntity.getRoleId());
        roleDTO.setRolename(roleEntity.getRoleName());
        return roleDTO;
    }

    public static List<RoleDTO> convertRolesEntityToRolesDTO(List<RoleEntity> rolesEntities) {
        if (rolesEntities == null) {
            return List.of();
        }
        return rolesEntities.stream()
                .map(roleEntity -> convertRoleEntityToRoleDTO(roleEntity))
                .collect(Collectors.toList());
    }
}
