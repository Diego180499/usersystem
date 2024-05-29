package com.project.usersystem.repository.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_status")
@Data
public class UserStatusEntity {
    @Id
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "status_name")
    private String statusName;

}
