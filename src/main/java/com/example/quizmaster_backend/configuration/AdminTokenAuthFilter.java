package com.example.quizmaster_backend.configuration;

import com.example.quizmaster_backend.service.AdminTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AdminTokenAuthFilter extends AbstractPreAuthenticatedProcessingFilter  {

    /*======================================*
     * FIELDS
     *======================================*/

    private final AdminTokenService adminTokenService;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a filter for checking if a valid admin token is given in a request.
     *
     * @param adminTokenService the service providing the valid admin token for the current application run
     *
     * @see <a href="https://stackoverflow.com/questions/48446708/securing-spring-boot-api-with-api-key-and-secret/48448901">
     *          Securing Spring Boot API with API key and secret
     *      </a>
     */
    @Autowired
    public AdminTokenAuthFilter(AdminTokenService adminTokenService) {
        this.adminTokenService = adminTokenService;
        setAuthenticationManager(getAuthenticationManagerForAdminToken());
    }

    /*======================================*
     * GETTERS WHICH NEED TO BE OVERRIDDEN
     *======================================*/

    /**
     * Returns the content of the Authorization header of the given request which should contain the valid admin token
     * if the request is privileged.
     *
     * @param request the HTTP request
     * @return the content of the Authorization header or null if it is missing
     */
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * Returns null since this request is not used for checking the admin token.
     * Needs to be overridden due to the extended class.
     *
     * @param request the HTTP request
     * @return always null
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    /*======================================*
     * ACTUAL AUTHENTICATION
     *======================================*/

    /**
     * Returns a custom authentication manager which checks if a request contains a valid admin token.
     * This authentication manager throws a {@link BadCredentialsException} if the admin token is missing or invalid.
     *
     * @return a custom authentication manager as described above
     */
    private AuthenticationManager getAuthenticationManagerForAdminToken() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                // get the token provided in the request
                String givenToken = (String) authentication.getPrincipal();

                // check the token against the correct admin token
                // => throw exception if invalid
                if (givenToken == null || !givenToken.equals(adminTokenService.getAdminToken())) {
                    throw new BadCredentialsException("Invalid admin token");
                }

                // token is valid
                authentication.setAuthenticated(true);
                return authentication;
            }
        };
    }
}