package com.project.usersystem.repository.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Data
@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private int idSession;

    @Column(name = "access_token")
    private String accessToken;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @Lazy
    @ManyToOne
    private UserEntity userEntity;

    @Column(name = "session_create")
    private Date sessionCreate;

    @Column(name = "session_expiration")
    private Date sessionExpiration;
}
