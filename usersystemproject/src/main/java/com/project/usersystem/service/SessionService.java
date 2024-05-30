package com.project.usersystem.service;


import com.project.usersystem.dto.commons.SessionDTO;
import com.project.usersystem.exception.UnauthenticatedException;
import com.project.usersystem.mappers.SessionMapper;
import com.project.usersystem.repository.SessionRepository;
import com.project.usersystem.repository.entities.SessionEntity;
import com.project.usersystem.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository sessionRepository;


    public SessionDTO createSession(UserEntity userEntity) {
        SessionEntity newSession = SessionMapper.createSessionEntity();
        newSession.setUserEntity(userEntity);
        newSession = sessionRepository.save(newSession);
        return SessionMapper.convertSessionEntityToSessionDTO(newSession);
    }

    public SessionEntity getActiveSession(String accessToken) {
        SessionEntity session = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new UnauthenticatedException("Invalid access token"));
        if (session.getSessionExpiration().before(new Date())) {
            throw new UnauthenticatedException("access token has expired");
        }
        return session;
    }


}
