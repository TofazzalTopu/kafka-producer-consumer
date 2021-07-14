package com.itc.notification.constants;

import org.springframework.stereotype.Component;

/**
 * @author Tofazzal
 */

@Component
public final class AppConstants {

    public static final String API_VERSION = "v1";

    public static final String SCHEDULER_STARTED = "Request notification scheduler started";
    public static final String SCHEDULER_END = "Request notification scheduler end";
    public static final String REQUEST_SCHEDULER = "REQUEST_SCHEDULER";
    public static final String LOGIN_PATH = "/api/v1/login";
    public static final String REGISTRATION_PATH = "/api/v1/users";
    public static final String JWT_SECRET = "SECURESECURELOGININFOTOTHESYSTEM";
    public static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/topic/request-list/**",
            "/push-message-mapping/**",
            "/topic/notification",
            "/",
            LOGIN_PATH,
            REGISTRATION_PATH
    };


    public static final String USER_REGISTERED_SUCCESS = "User saved successfully.";
    public static final String USER_NAME_ALREADY_EXIST = "User already exist with the username: ";
    public static final String USER_FETCH_SUCCESS = "User fetch successfully.";
    public static final String LOGOUT_SUCCESS = "You have been logged out successfully.";
}
