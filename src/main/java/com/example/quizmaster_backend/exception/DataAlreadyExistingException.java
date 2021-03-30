package com.example.quizmaster_backend.exception;

public class DataAlreadyExistingException extends ErrorDetailsException {

    /**
     * Creates an instance of the exception with the given error details.
     *
     * @param errorDetails the details for the exception
     */
    public DataAlreadyExistingException(String errorDetails) {
        super(errorDetails);
    }
}
