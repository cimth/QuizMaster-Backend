package com.example.quizmaster_backend.controller;

import com.example.quizmaster_backend.exception.RestExceptionHandler;
import com.example.quizmaster_backend.model.Question;
import com.example.quizmaster_backend.model.dto.request.NewOrUpdateQuestionDto;
import com.example.quizmaster_backend.model.dto.response.QuestionPlayFormatDto;
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
     * Adds the question given in the request body to the database.
     *
     * @param newQuestionData the question's data
     *
     * @return the created question
     */
    @PostMapping
    public Question addQuestion(@Valid @NotNull @RequestBody NewOrUpdateQuestionDto newQuestionData) {
        return this.questionService.addQuestion(newQuestionData.getQuestionText(), newQuestionData.getCorrectAnswer(), newQuestionData.getWrongAnswers());
    }

    /*======================================*
     * READ MAPPING
     *======================================*/

    /**
     * GET: /question
     * <br />
     * Returns all questions of the database in raw format.
     * <br />
     * This request is supposed to be used for managing the questions.
     * For playing a Quiz session use the request of a single question in play format which has
     * applied all answers to corresponding answer letters etc.
     *
     * @return all questions of the database in raw format
     */
    @GetMapping
    public Iterable<Question> getAllQuestions() {
        return questionService.getAllQuestionsInRawFormat();
    }

    /**
     * GET: /question/{id}
     * <br />
     * Returns the question in raw format with the given id.
     * <br />
     * If request is invalid or the question does not exist, the {@link RestExceptionHandler} will return an error
     * message.
     *
     * @param id the id of the requested question
     * @param locale the locale of the user
     * @return the requested question in raw format if the request is valid
     */
    @GetMapping("/{id}")
    public Question getQuestionInRawFormat(
            @PathVariable("id") Long id,
            Locale locale) {
        return questionService.getRawQuestionById(id, locale);
    }

    /**
     * GET: /question/{id}/playFormat
     * <br />
     * Returns the question with the given id in a format which is intended to be used when playing a quiz.
     * As an example, the answers are shuffled and provided with corresponding answer letters.
     * <br />
     * If request is invalid or the question does not exist, the {@link RestExceptionHandler} will return an error
     * message.
     *
     * @param id the id of the requested question
     * @param locale the locale of the user
     * @return the requested question in play format if the request is valid
     */
    @GetMapping("/{id}/playFormat")
    public QuestionPlayFormatDto getQuestionInPlayFormat(
            @PathVariable("id") Long id,
            Locale locale) {
        return questionService.getPlayFormatQuestionById(id, locale);
    }

    /*======================================*
     * UPDATE MAPPING
     *======================================*/

    /**
     * PUT: /question/{id}
     * <br />
     * Updates the question given by its ID with the data in the request body inside the database.
     * <br />
     * If request is invalid or the question does not exist, the {@link RestExceptionHandler} will return an error
     * message.
     *
     * @param updateQuestionData the (updated) question's data
     * @param locale the locale of the user
     * @return a success message if the request is valid
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> addQuestion(
            @PathVariable("id") Long id,
            @Valid @NotNull @RequestBody NewOrUpdateQuestionDto updateQuestionData,
            Locale locale) {
        this.questionService.updateQuestion(id, updateQuestionData.getQuestionText(), updateQuestionData.getCorrectAnswer(), updateQuestionData.getWrongAnswers(), locale);
        return ResponseEntity.ok(messageSource.getMessage("QuestionController.updated", null, locale));
    }

    /*======================================*
     * DELETE MAPPING
     *======================================*/

    /**
     * DELETE: /question/{id}
     * <br />
     * Deletes the question given by its ID.
     * <br />
     * If request is invalid or the question does not exist, the {@link RestExceptionHandler} will return an error
     * message.
     *
     * @param locale the locale of the user
     * @return a success message if the request is valid
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> addQuestion(
            @PathVariable("id") Long id,
            Locale locale) {
        this.questionService.deleteQuestion(id, locale);
        return ResponseEntity.ok(messageSource.getMessage("QuestionController.deleted", null, locale));
    }
}
