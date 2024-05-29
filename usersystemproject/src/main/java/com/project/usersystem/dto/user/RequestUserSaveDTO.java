package com.project.usersystem.dto.user;

import com.project.usersystem.dto.commons.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserSaveDTO {
    private UserDTO user;
}
