package com.project.usersystem.repository.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "role")
@Data
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "ts_insert")
    private Date tsInsert;

    @Column(name = "ts_update")
    private Date tsUpdate;


}
