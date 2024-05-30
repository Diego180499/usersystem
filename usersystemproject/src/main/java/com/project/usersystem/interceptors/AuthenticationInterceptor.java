package com.project.usersystem.interceptors;

import java.lang.reflect.Method;

import com.project.usersystem.anotations.AuthenticationRequired;
import com.project.usersystem.repository.entities.SessionEntity;
import com.project.usersystem.service.SessionService;
import com.project.usersystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor {

    private final UserService userService;
    private final SessionService sessionService;

    @Before("@annotation(com.project.usersystem.anotations.AuthenticationRequired)")
    public void validateAccessToken(JoinPoint jp) {
        var accessToken = (String) jp.getArgs()[jp.getArgs().length - 1];

        AuthenticationRequired authenticationRequired = fetchAuthenticationRequired(jp);
        String[] allowedRoles = authenticationRequired.allowedRoles();
        validateAccessTokenWithAllowedRoles(accessToken, allowedRoles);
    }

    private AuthenticationRequired fetchAuthenticationRequired(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(AuthenticationRequired.class);
    }

    private void validateAccessTokenWithAllowedRoles(String accessToken, String[] allowedRoles) {
        SessionEntity session = sessionService.getActiveSession(accessToken);
        userService.checkIfUserHasAllowedRoles(session.getUserEntity().getUserId(), allowedRoles);
    }
}
