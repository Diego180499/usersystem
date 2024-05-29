package com.project.usersystem.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;
    private String name;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password_hashed")
    private String passwordHashed;

    @Column(name = "ts_insert")
    private Date tsInsert;

    @Column(name = "ts_update")
    private Date tsUpdate;

    @JoinColumn(name = "status", referencedColumnName = "status_id")
    @Lazy
    @ManyToOne
    private UserStatusEntity status;

}
