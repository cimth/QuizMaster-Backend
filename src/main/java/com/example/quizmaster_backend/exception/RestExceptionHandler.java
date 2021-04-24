package com.example.quizmaster_backend.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.aspectj.weaver.ast.Or;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    /*======================================*
     * FIELDS
     *======================================*/

    private final MessageSource messageSource;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     *
     * @param messageSource
     */
    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*======================================*
     * EXCEPTION HANDLING FOR NOT MATCHED ANNOTATIONS
     *======================================*/

    /**
     * Returns the error details of the exception to the user in JSON format.
     * Occurs where the given data in a request body are of the right type but their value is not valid.
     *
     * @param e the exception
     * @return the JSON object
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidMethodArgument(MethodArgumentNotValidException e) {

        // put the errors into a <field with error, error message>-map
        Map<String, String> errorDetails = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorDetails.put(error.getField(), error.getDefaultMessage());
        });

        // create response map
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", errorDetails);

        // return response map
        return response;
    }

    /*======================================*
     * EXCEPTION HANDLING FOR JSON
     *======================================*/

    /**
     * Returns an error message that indicates that at least one type in the given JSON request body is of the
     * wrong type.
     *
     * @param e the exception
     * @param locale the locale of the user
     * @return the error message string
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    public String handleJsonTypeException(InvalidFormatException e, Locale locale) {
        return this.messageSource.getMessage("RestExceptionHandler.InvalidFormatException", null, locale);
    }

    /**
     * Returns an error message that indicates that the given JSON request body is malformatted.
     *
     * @param e the exception
     * @param locale the locale of the user
     * @return the error message string
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(JsonParseException.class)
    public String handleJsonFormatException(JsonParseException e, Locale locale) {
        return this.messageSource.getMessage("RestExceptionHandler.JsonParseException", null, locale);
    }

    /*======================================*
     * EXCEPTION HANDLING FOR CUSTOM EXCEPTIONS
     *======================================*/

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

    /**
     * Returns the error details of the exception to the user as plain string.
     * Occurs when an user requests data that do not exist or when an user wants to perform a request on data that
     * do not exist.
     *
     * @param ex the exception
     * @return the error message string
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BadOperationException.class)
    public String handleBadOperation(BadOperationException ex) {
        return ex.getErrorDetails();
    }

    /**
     * Returns the error details of the exception to the user as plain string.
     * Occurs when an user requests data that do not exist or when an user wants to perform a request on data that
     * do not exist.
     *
     * @param ex the exception
     * @return the error message string
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAlreadyExistingException.class)
    public String handleAlreadyExisting(DataAlreadyExistingException ex) {
        return ex.getErrorDetails();
    }
}
