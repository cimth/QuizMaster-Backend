package com.example.quizmaster_backend.exception;

public abstract class ErrorDetailsException extends RuntimeException {

    /*======================================*
     * FIELDS
     *======================================*/

    private String errorDetails;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates an instance of the exception with the given error details.
     *
     * @param errorDetails the details for the exception
     */
    public ErrorDetailsException(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    /*======================================*
     * GETTER
     *======================================*/

    /**
     * @return the error details of the exception
     */
    public String getErrorDetails() {
        return errorDetails;
    }
}
