package com.example.quizmaster_backend.repository;

import com.example.quizmaster_backend.model.PredefinedQuiz;
import com.example.quizmaster_backend.model.PredefinedQuizQuestion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PredefinedQuizQuestionsRepository extends CrudRepository<PredefinedQuizQuestion, Long> {
    // CRUD methods are automatically implemented through CrudRepository

    // add additional methods (implementation will be done automatically by Spring)
    public List<PredefinedQuizQuestion> findAllByQuizId(Long quizId);
}
