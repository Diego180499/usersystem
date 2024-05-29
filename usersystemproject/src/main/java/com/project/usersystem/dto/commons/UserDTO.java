package com.project.usersystem.dto.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String name;
    private String username;
    private String password;
    private String id;
    private String status;
    private Long tsInsert;
}
