package com.example.quizmaster_backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class AdminTokenExceptionHandler implements AuthenticationEntryPoint {

    /*======================================*
     * FIELDS
     *======================================*/

    private final MessageSource messageSource;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates an exception handler for handling exceptions that are caused by an invalid admin token.
     *
     * @param messageSource a message source for localized strings from resources
     */
    @Autowired
    public AdminTokenExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*======================================*
     * EXCEPTION HANDLING
     *======================================*/

    /**
     * Handles the given authentication exception via writing an error message in the HTTP response.
     *
     * @param request the HTTP request causing the exception
     * @param response the HTTP response to be returned to the user
     * @param e the thrown exception
     *
     * @throws IOException can be thrown when writing the response
     *
     * @see
     *      <a href="https://stackoverflow.com/questions/50941942/handle-spring-security-authentication-exceptions-in-resourceserver">
     *          Handle Spring Security authentication exceptions
     *      </a>
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        // get locale of the user
        Locale locale = request.getLocale();

        // set http error status
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // write localized error message
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(this.messageSource.getMessage("AdminTokenAuthFailureHandler.ErrorMessage", null, locale));
        response.getWriter().flush();
    }
}
