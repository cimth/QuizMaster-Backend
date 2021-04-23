package com.example.quizmaster_backend.configuration;

import com.example.quizmaster_backend.exception.AdminTokenExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*======================================*
     * FIELDS
     *======================================*/

    private final AdminTokenAuthFilter adminTokenAuthFilter;
    private final AdminTokenExceptionHandler adminTokenExceptionHandler;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a configuration for the web security which handles topics like forcing an authorization
     * for incoming requests.
     *
     * @param adminTokenAuthFilter a filter for checking if a valid admin token is given
     * @param adminTokenExceptionHandler an exception handler for authentication errors
     */
    @Autowired
    public WebSecurityConfig(
            AdminTokenAuthFilter adminTokenAuthFilter,
            AdminTokenExceptionHandler adminTokenExceptionHandler) {

        this.adminTokenAuthFilter = adminTokenAuthFilter;
        this.adminTokenExceptionHandler = adminTokenExceptionHandler;
    }

    /*======================================*
     * CONFIGURATION
     *======================================*/

    /**
     * Configures security for web connections.
     * <br />
     * Defines on which requests an authorization with the admin token is needed to proceed.
     * Also defines the exception handler for authentication errors.
     * <br />
     * Enables HTTPS while the HTTPS configuration (e.g. for the used certificate) is placed in the resources directory.
     *
     * @see <a href="https://stackoverflow.com/questions/48446708/securing-spring-boot-api-with-api-key-and-secret/48448901">
     *          Securing Spring Boot API with API key and secret
     *      </a>
     *
     * @see <a href="https://www.thomasvitale.com/https-spring-boot-ssl-certificate/">
     *          How to enable HTTPS in a Spring Boot Java application
     *      </a>
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // first process CORS, needed for allowed cross origin rules with Angular frontend
        // see: https://www.baeldung.com/spring-cors#cors-with-spring-security
        http.cors();

        // disable CSRF, has to be set to use admin token for authentication
        http.csrf().disable();

        // force https
        // see: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
        http.requiresChannel()
                .anyRequest()
                .requiresSecure();

        // do not use sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // add authorization via admin token
        http
            .authorizeRequests()
                // public requests
                .antMatchers(HttpMethod.GET, "/quiz/**").permitAll()
                .antMatchers(HttpMethod.GET, "/question").permitAll()
                .antMatchers(HttpMethod.GET, "/question/{\\d+}").permitAll()
                .antMatchers(HttpMethod.GET, "/question/{\\d+}/playFormat").permitAll()
                // authorize any other request
                .anyRequest().authenticated()
            .and()
                // check for admin token when a request should be authenticated
                .addFilter(adminTokenAuthFilter)
                .exceptionHandling()
                    .authenticationEntryPoint(adminTokenExceptionHandler);
    }
}
