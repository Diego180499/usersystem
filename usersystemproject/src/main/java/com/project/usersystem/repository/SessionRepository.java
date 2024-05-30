package com.project.usersystem.repository;


import com.project.usersystem.repository.cruds.SessionCrud;
import com.project.usersystem.repository.entities.SessionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SessionRepository {

    private SessionCrud sessionCrud;

    public SessionEntity save(SessionEntity sessionEntity) {
        return sessionCrud.save(sessionEntity);
    }

    public Optional<SessionEntity> findByAccessToken(String accessToken) {
        try {
            return Optional.ofNullable(sessionCrud.findByAccessToken(accessToken));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
