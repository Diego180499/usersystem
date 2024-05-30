package com.project.usersystem.repository.cruds;

import com.project.usersystem.repository.entities.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCrud extends CrudRepository<SessionEntity, Integer> {

    SessionEntity findByAccessToken(String accessToken);
}
