package com.project.usersystem.repository.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


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

    @OneToMany(mappedBy = "roleEntity")
    private List<UserRoleEntity> userRoleEntities;


}
