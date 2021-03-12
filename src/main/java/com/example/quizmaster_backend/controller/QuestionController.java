package com.example.quizmaster_backend.controller;

import com.example.quizmaster_backend.model.dto.response.QuestionDto;
import com.example.quizmaster_backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/question")
public class QuestionController {

    /*======================================*
     * FIELDS
     *======================================*/

    private final QuestionService questionService;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a REST controller for requests affecting questions.
     */
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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
