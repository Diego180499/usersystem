package com.project.usersystem.dto.userRole;

import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.dto.commons.UserDTO;
import lombok.Data;


@Data
public class RequestAddUserRoleDTO {

    private UserDTO user;
    private RoleDTO role;
}
