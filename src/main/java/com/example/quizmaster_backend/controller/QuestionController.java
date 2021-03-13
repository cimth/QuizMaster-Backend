package com.example.quizmaster_backend.controller;

import com.example.quizmaster_backend.model.dto.request.NewOrUpdatedQuestionDto;
import com.example.quizmaster_backend.model.dto.response.QuestionDto;
import com.example.quizmaster_backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@RestController
@RequestMapping("/question")
public class QuestionController {

    /*======================================*
     * FIELDS
     *======================================*/

    private final MessageSource messageSource;
    private final QuestionService questionService;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a REST controller for requests affecting questions.
     *
     * @param messageSource a message source for localized strings from resources
     */
    @Autowired
    public QuestionController(MessageSource messageSource, QuestionService questionService) {
        this.messageSource = messageSource;
        this.questionService = questionService;
    }

    /*======================================*
     * CREATE MAPPING
     *======================================*/

    /**
     * POST: /question
     * <br />
     * Adds the question given in the request body to the database if the request contains the admin token.
     *
     * @param newQuestionData the question's data
     * @param locale the locale of the user
     * @return a success message
     */
    @PostMapping
    public ResponseEntity<String> addQuestion(
            @Valid @NotNull @RequestBody NewOrUpdatedQuestionDto newQuestionData,
            Locale locale) {

        return ResponseEntity.ok(messageSource.getMessage("QuestionController.created", null, locale));
    }

    /*======================================*
     * READ MAPPING
     *======================================*/

    @GetMapping("/{id}")
    public QuestionDto getQuestion(
            @PathVariable("id") Long id,
            Locale locale
    ) {
        return questionService.findQuestionById(id, locale);
    }
}
