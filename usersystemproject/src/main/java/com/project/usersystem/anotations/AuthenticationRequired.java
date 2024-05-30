package com.project.usersystem.anotations;

import java.lang.annotation.*;

/**
 * Annotation to validate require Access Token in methods annotated with it
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenticationRequired {
    String[] allowedRoles() default {"ADMIN"};
}
