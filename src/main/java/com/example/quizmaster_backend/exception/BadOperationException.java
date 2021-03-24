package com.example.quizmaster_backend.exception;

public class BadOperationException extends ErrorDetailsException {

    /**
     * Creates an instance of the exception with the given error details.
     *
     * @param errorDetails the details for the exception
     */
    public BadOperationException(String errorDetails) {
        super(errorDetails);
    }
}
