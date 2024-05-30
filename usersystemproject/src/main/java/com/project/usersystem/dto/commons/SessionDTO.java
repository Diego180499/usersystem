package com.project.usersystem.dto.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDTO {
    private String accessToken;
    private String userId;
    private Date sessionCreate;
    private Date sessionExpiration;

}
