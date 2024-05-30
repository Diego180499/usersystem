package com.project.usersystem.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Entity
@Table(name = "user_role")
@Data
public class UserRoleEntity {

    @Id
    @Column(name = "user_role_id")
    private String userRoleId;


    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @Lazy
    @ManyToOne
    private UserEntity userEntity;


    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @Lazy
    @ManyToOne
    private RoleEntity roleEntity;

    @Column(name = "ts_insert")
    private Date tsInsert;

    @Column(name = "ts_update")
    private Date tsUpdate;
}
