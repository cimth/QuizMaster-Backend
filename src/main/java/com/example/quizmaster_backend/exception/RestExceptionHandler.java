package com.example.quizmaster_backend.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Returns the error details of the exception to the user as plain string.
     * Occurs when an user requests data that do not exist or when an user wants to perform a request on data that
     * do not exist.
     *
     * @param ex the exception
     * @return the error message string
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public String handleNotFound(DataNotFoundException ex) {
        return ex.getErrorDetails();
    }
}
