package com.example.quizmaster_backend.controller;

import com.example.quizmaster_backend.exception.RestExceptionHandler;
import com.example.quizmaster_backend.model.PredefinedQuiz;
import com.example.quizmaster_backend.model.dto.request.NewOrUpdateQuestionDto;
import com.example.quizmaster_backend.model.dto.request.NewPredefinedQuizDto;
import com.example.quizmaster_backend.model.dto.request.NewRandomQuizDto;
import com.example.quizmaster_backend.model.dto.request.UpdatePredefinedQuizDto;
import com.example.quizmaster_backend.model.dto.response.PredefinedQuizDto;
import com.example.quizmaster_backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/quiz")
public class QuizController {

    /*======================================*
     * FIELDS
     *======================================*/

    private final MessageSource messageSource;
    private final QuizService quizService;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a REST controller for requests affecting quizzes.
     *
     * @param messageSource a message source for localized strings from resources
     */
    @Autowired
    public QuizController(MessageSource messageSource, QuizService quizService) {
        this.messageSource = messageSource;
        this.quizService = quizService;
    }

    /*======================================*
     * CREATE MAPPING FOR PREDEFINED QUIZ (WITH ALREADY CHOSEN QUESTIONS)
     *======================================*/

    /**
     * POST: /quiz
     * <br />
     * Adds an (empty) predefined quiz with the quiz name given in the request body.
     *
     * @param newPredefinedQuizDto the quiz' data (currently only a quiz name)
     * @param locale the locale of the user
     *
     * @return a success message
     */
    @PostMapping
    public PredefinedQuiz createPredefinedQuiz(
            @Valid @NotNull @RequestBody NewPredefinedQuizDto newPredefinedQuizDto,
            Locale locale) {
        return this.quizService.createPredefinedQuiz(newPredefinedQuizDto.getQuizName(), locale);
    }

    /**
     * PUT: /quiz/{id}
     * <br />
     * Updates the predefined quiz given by its ID. With this request you can change the quiz name, apply
     * questions to the quiz or delete existing question associations.
     *
     * @param updatePredefinedQuizDto the quiz' data (currently only a quiz name)
     * @param locale the locale of the user
     * @return a success message
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePredefinedQuiz(
            @PathVariable("id") Long id,
            @Valid @NotNull @RequestBody UpdatePredefinedQuizDto updatePredefinedQuizDto,
            Locale locale) {
        this.quizService.updatePredefinedQuiz(id, updatePredefinedQuizDto.getQuizName(), updatePredefinedQuizDto.getQuizQuestions(), locale);
        return ResponseEntity.ok(messageSource.getMessage("QuizController.updated", null, locale));
    }

    /*======================================*
     * READ MAPPING FOR GETTING RANDOM QUIZZES (I.E. AN ARRAY OF RANDOM QUESTIONS)
     *======================================*/

    /**
     * GET: /quiz/random
     * <br />
     * Returns an array with question IDs that can be used to play a quiz in the frontend.
     * <br />
     * You can optionally give a list with IDs of already used Questions. If this list is provided the backend will
     * not return any of the already used question IDs as long as there are enough other questions left.
     *
     * @param locale the locale of the user
     * @return an array with X question IDs
     */
    @GetMapping("/random")
    public List<Long> getRandomQuiz(
            @Valid @NotNull @RequestBody NewRandomQuizDto newRandomQuizDto,
            Locale locale) {
        // create empty list if there are not given any already used question
        List<Long> alreadyUsedQuestions = newRandomQuizDto.getAlreadyUsedQuestions();
        if (alreadyUsedQuestions == null) {
            alreadyUsedQuestions = new ArrayList<>();
        }

        // return question IDs for random quiz
        return quizService.getRandomQuiz(newRandomQuizDto.getQuestionCount(), alreadyUsedQuestions, locale);
    }

    /*======================================*
     * READ MAPPING FOR PREDEFINED QUIZZES (WITH ALREADY CHOSEN QUESTIONS)
     *======================================*/

    /**
     * GET: /quiz/predefined
     * <br />
     * Returns an array with all predefined quizzes and their important data (count of questions, is playable)
     * that are stored in the database.
     *
     * @return an array with the response DTOs of predefined quizzes
     */
    @GetMapping("/predefined")
    public List<PredefinedQuizDto> getAllPredefinedQuizzes() {
        return quizService.getAllPredefinedQuizzes();
    }

    /**
     * GET: /quiz/{id}
     * <br />
     * Returns an array with the question IDs of the predefined Quiz identified by the given ID.
     * This array can be used to play a quiz in the frontend.
     *
     * @param locale the locale of the user
     * @return an array with the question IDs of the predefined quiz
     */
    @GetMapping("/{id}")
    public List<Long> getPredefinedQuiz(
            @PathVariable("id") Long id,
            Locale locale) {
        return quizService.getPredefinedQuiz(id, locale);
    }

    /*======================================*
     * DELETE MAPPING FOR PREDEFINED QUIZZES
     *======================================*/

    /**
     * DELETE: /quiz/{id}
     * <br />
     * Deletes the predefined quiz with the given ID.
     * <br />
     * If request is invalid or the quiz does not exist, the {@link RestExceptionHandler} will return an error
     * message.
     *
     * @param locale the locale of the user
     * @return a success message if the request is valid
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> addQuestion(
            @PathVariable("id") Long id,
            Locale locale) {
        this.quizService.deletePredefinedQuiz(id, locale);
        return ResponseEntity.ok(messageSource.getMessage("QuizController.deleted", null, locale));
    }
}
