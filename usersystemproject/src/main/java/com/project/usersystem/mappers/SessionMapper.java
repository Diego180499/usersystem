package com.project.usersystem.mappers;

import com.project.usersystem.dto.commons.SessionDTO;
import com.project.usersystem.repository.entities.SessionEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class SessionMapper {

    public static final int ONE_DAY = 1;

    public static SessionEntity createSessionEntity() {
        SessionEntity newSession = new SessionEntity();
        newSession.setAccessToken(UUID.randomUUID().toString());
        newSession.setSessionCreate(new Date());
        newSession.setSessionExpiration(sumarDiasFecha(new Date(), ONE_DAY));
        return newSession;
    }


    public static SessionDTO convertSessionEntityToSessionDTO(SessionEntity sessionEntity) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setAccessToken(sessionEntity.getAccessToken());
        sessionDTO.setSessionCreate(sessionEntity.getSessionCreate());
        sessionDTO.setSessionExpiration(sessionEntity.getSessionExpiration());
        return sessionDTO;
    }


    public static Date sumarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }


}
